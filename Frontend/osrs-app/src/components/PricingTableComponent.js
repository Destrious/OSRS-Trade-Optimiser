import React, { useState, useEffect } from 'react';
import { importImage } from '../images';

const PricingTableComponent = () => {
    const [data, setData] = useState([]);
    const [images, setImages] = useState({});

    useEffect(() => {
        fetch('http://localhost:8080/items/?limit=50')
            .then(response => response.json())
            .then(async data => {
                const images = {};
                for (const item of data) {
                    images[item.icon] = await importImage(item.icon);
                }
                setImages(images);
                setData(data);
            });
    }, []);

    return (
        <table>
            <thead>
            <tr>
                <th>Image</th>
                <th>Item Name</th>
                <th>Item High</th>
                <th>High Time</th>
                <th>Item Low</th>
                <th>Low Time</th>
            </tr>
            </thead>

            <tbody>
            {data.map(item => (
                <tr key={item.id}>
                    <td><img src={images[item.icon]} alt={item.icon} /></td>
                    <td>{item.name}</td>
                    <td>{item.high}</td>
                    <td>{new Date(item.highTime * 1000).toLocaleString()}</td>
                    <td>{item.low}</td>
                    <td>{new Date(item.lowTime * 1000).toLocaleString()}</td>
                </tr>
            ))}
            </tbody>
        </table>
    );
};

export default PricingTableComponent;
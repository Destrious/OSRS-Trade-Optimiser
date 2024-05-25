import React, { useState, useEffect } from 'react';
import { importImage } from '../images';
import Pagination from '@mui/material/Pagination';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';

const PricingTableComponent = () => {
    const [data, setData] = useState([]);
    const [images, setImages] = useState({});

    const [loading, setLoading] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [postsPerPage, setPostsPerPage] = useState(50);

    useEffect(() => {
        const fetchPosts = async () => {
            setLoading(true);
            try {
                const response = await fetch("http://localhost:8080/items/?limit=50")
                const data = await response.json();
                setData(data);
                setLoading(false);
                data.forEach(async item => {
                    images[item.icon] = await importImage(item.icon);
                });
            } catch (error) {
                console.log(error);
            }
        };
        fetchPosts();
    }, []);

    // get current data
    const indexOfLastPost = currentPage * postsPerPage;
    const indexOfFirstPost = indexOfLastPost - postsPerPage;
    const currentData = data.slice(indexOfFirstPost, indexOfLastPost);

    // change page
    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    if (loading) {
        return <h1>Loading...</h1>;
    }

    return (
        <TableContainer>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>ID</TableCell>
                        <TableCell>Image</TableCell>
                        <TableCell>Item Name</TableCell>
                        <TableCell>Item High</TableCell>
                        <TableCell>High Time</TableCell>
                        <TableCell>Item Low</TableCell>
                        <TableCell>Low Time</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {currentData.map((item, index) => (
                        <TableRow key={index}>
                            <TableCell>{item.id}</TableCell>
                            <TableCell><img src={images[item.icon]} alt={item.icon} height="50" /></TableCell>
                            <TableCell>{item.name}</TableCell>
                            <TableCell>{item.high}</TableCell>
                            <TableCell>{new Date(item.highTime * 1000).toLocaleString()}</TableCell>
                            <TableCell>{item.low}</TableCell>
                            <TableCell>{new Date(item.lowTime * 1000).toLocaleString()}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
            <Pagination count={Math.ceil(data.length / postsPerPage)}
                        page={currentPage}
                        onChange={(event, page) => paginate(page)} />
        </TableContainer>
    );
};

export default PricingTableComponent;
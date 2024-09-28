import React, { useState, useEffect, useCallback } from 'react';
import { importImage } from '../images';
import Pagination from '@mui/material/Pagination';
import { TextField, Checkbox, Tab, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import SearchFilterCheckbox from './searchFilterCheckbox';

const PricingTableComponent = () => {
    const [data, setData] = useState([]);
    const [images, setImages] = useState({});
    const [loading, setLoading] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [postsPerPage, setPostsPerPage] = useState(50);
    const [searchQuery, setSearchQuery] = useState('');
    const [thirdAgeChecked, setThirdAgeChecked] = useState(false);

    useEffect(() => {
        const fetchPosts = async () => {
            setLoading(true);
            try {
                const response = await fetch("http://localhost:8080/profit/")
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

    // Handle search input change
    const handleSearchChange = (e) => {
        setSearchQuery(e.target.value);
        setCurrentPage(1);
    };

    const handleThirdAgeChange = useCallback((event) => {
        setThirdAgeChecked(event.target.checked);
    }, []);

    // Filter data based on search query
    const filteredData = data.filter(item => 
        item.name.toLowerCase().includes(searchQuery.toLowerCase()) &&
        (!thirdAgeChecked || !item.name.toLowerCase().includes('3rd'))
    );

    // get current data
    const indexOfLastPost = currentPage * postsPerPage;
    const indexOfFirstPost = indexOfLastPost - postsPerPage;
    const currentData = filteredData.slice(indexOfFirstPost, indexOfLastPost);

    // change page
    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    if (loading) {
        return <h1>Loading...</h1>;
    }

    return (
        <div>
            <TextField 
                label="Search" 
                variant="outlined" 
                value={searchQuery} 
                onChange={handleSearchChange} 
                style={{ marginBottom: '20px' }}
            />
            <SearchFilterCheckbox
                name="Exclude 3rd Age"
                checked={thirdAgeChecked}
                onChange={handleThirdAgeChange}
            />
            <TableContainer>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Image</TableCell>
                            <TableCell>Item Name</TableCell>
                            <TableCell>Buy Price</TableCell>
                            <TableCell>Buy Time</TableCell>
                            <TableCell>Sell Price</TableCell>
                            <TableCell>Sell Time</TableCell>
                            <TableCell>Profit</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {currentData.map((item, index) => (
                            <TableRow key={index}>
                                <TableCell>{item.id}</TableCell>
                                <TableCell><img src={images[item.icon]} alt={item.icon} height="50" /></TableCell>
                                <TableCell>{item.name}</TableCell>
                                <TableCell>{item.buyPrice}</TableCell>
                                <TableCell>{new Date(item.buyTime * 1000).toLocaleString()}</TableCell>
                                <TableCell>{item.sellPrice}</TableCell>
                                <TableCell>{new Date(item.sellTime * 1000).toLocaleString()}</TableCell>
                                <TableCell>{item.profitRaw}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
                <Pagination count={Math.ceil(filteredData.length / postsPerPage)}
                            page={currentPage}
                            onChange={(event, page) => paginate(page)} />
            </TableContainer>
        </div>
    );
};

export default PricingTableComponent;
import React, { useState, useEffect, useCallback } from 'react';
import { importImage } from '../images';
import Pagination from '@mui/material/Pagination';
import { styled, InputLabel, MenuItem, FormControl, Select, TextField, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import SearchFilterCheckbox from './SearchFilterCheckbox';

const PricingTableComponent = () => {
    const [data, setData] = useState([]);
    const [images, setImages] = useState({});
    const [loading, setLoading] = useState(false);
    const [currentPage, setCurrentPage] = useState(1);
    const [postsPerPage, setPostsPerPage] = useState(50);
    const [searchQuery, setSearchQuery] = useState('');
    const [thirdAgeChecked, setThirdAgeChecked] = useState(false);
    const [outlierChecked, setOutlierChecked] = useState(false);
    const [orderBy, setOrderBy] = useState('profitRaw');

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

    const handleOutlierChange = useCallback((event) => {
        setOutlierChecked(event.target.checked);
    }, []);

    const handleOrderByChange = (event) => {
        setOrderBy(event.target.value);
        setCurrentPage(1);
    };

    // Filter data based on search query
    var filteredData = data.filter(item => 
        item.name.toLowerCase().includes(searchQuery.toLowerCase()) &&
        (!thirdAgeChecked || !item.name.toLowerCase().includes('3rd')) &&
        (!outlierChecked || item.profitPercent <= 10000)
    );

    // Sort data based on orderBy
    filteredData = filteredData.sort((a, b) => {
        if (orderBy === 'profitRaw') {
            return b.profitRaw - a.profitRaw;
        } else {
            return b.profitPercent - a.profitPercent;
        }
    });

    // get current data
    const indexOfLastPost = currentPage * postsPerPage;
    const indexOfFirstPost = indexOfLastPost - postsPerPage;
    const currentData = filteredData.slice(indexOfFirstPost, indexOfLastPost);

    // change page
    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    if (loading) {
        return <h1>Loading...</h1>;
    }

    const columnHeaders = [
        "ID",
        "Image",
        "Item Name",
        "Buy Time",
        "Sell Time",
        "Buy Price",
        "Sell Price",
        "Profit",
        "Profit (w tax)",
        "Profit Margin"
    ];

    const StyledTableRow = styled(TableRow)(({ theme }) => ({
        '&': {
          backgroundColor: theme.palette.action.hover, // Apply background color to all rows
        },
      }));

    return (
        <div>
            <TextField 
                label="Search" 
                variant="outlined" 
                value={searchQuery} 
                onChange={handleSearchChange} 
                style={{ marginBottom: '20px', width: '35%'}}
            />
            <FormControl variant="standard" style={{marginLeft: '10px'}}>
                <InputLabel>Order By</InputLabel>
                <Select value={orderBy} label="Order By" onChange={handleOrderByChange} style={{ minWidth: '100px'}}>
                    <MenuItem value={"profitRaw"}>Profit</MenuItem>
                    <MenuItem value={"profitPercent"}>Profit Margin</MenuItem>
                </Select>
            </FormControl>
            <SearchFilterCheckbox
                name="Exclude 3rd Age"
                checked={thirdAgeChecked}
                onChange={handleThirdAgeChange}
            />
            <SearchFilterCheckbox
                name="Exclude Outliers"
                checked={outlierChecked}
                onChange={handleOutlierChange}
            />
            <TableContainer>
                <Pagination count={Math.ceil(filteredData.length / postsPerPage)}
                                page={currentPage}
                                onChange={(event, page) => paginate(page)} />
                <Table>
                    <TableHead>
                        <StyledTableRow> 
                            {columnHeaders.map((header) => (
                                <TableCell key={header} style={{ fontWeight: 'bold' }}>{header}</TableCell>
                            ))}
                        </StyledTableRow>
                    </TableHead>
                    <TableBody>
                        {currentData.map((item, index) => (
                            <StyledTableRow key={index}>
                                <TableCell>{item.id}</TableCell>
                                <TableCell><img src={images[item.icon]} alt={item.icon} height="50" style={{ maxWidth: '60px'}}/></TableCell>
                                <TableCell style={{ maxWidth: '120px'}}>{item.name}</TableCell>
                                <TableCell>
                                    {(() => {
                                        const minutes = Math.floor((Date.now() - item.buyTime * 1000) / 60000);
                                        const hours = Math.floor(minutes / 60);
                                        const days = Math.floor(hours / 24);
                                        const remainingHours = hours % 24;
                                        const remainingMinutes = minutes % 60;
                                        
                                        if (days > 0) {
                                            return `${days} day${days > 1 ? 's' : ''} ${remainingHours} hr ago`;
                                        } else if (hours > 0) {
                                            return `${hours} hr ${remainingMinutes} min ago`;
                                        } else {
                                            return `${minutes} min ago`;
                                        }
                                    })()}
                                </TableCell>
                                <TableCell>
                                    {(() => {
                                        const minutes = Math.floor((Date.now() - item.sellTime * 1000) / 60000);
                                        const hours = Math.floor(minutes / 60);
                                        const days = Math.floor(hours / 24);
                                        const remainingHours = hours % 24;
                                        const remainingMinutes = minutes % 60;
                                        
                                        if (days > 0) {
                                            return `${days} day${days > 1 ? 's' : ''} ${remainingHours} hr ago`;
                                        } else if (hours > 0) {
                                            return `${hours} hr ${remainingMinutes} min ago`;
                                        } else {
                                            return `${minutes} min ago`;
                                        }
                                    })()}
                                </TableCell>
                                <TableCell>{new Intl.NumberFormat().format(item.buyPrice)}</TableCell>
                                <TableCell>{new Intl.NumberFormat().format(item.sellPrice)}</TableCell>
                                <TableCell>{new Intl.NumberFormat().format(item.profitRaw)}</TableCell>
                                <TableCell>
                                    {new Intl.NumberFormat().format(
                                        item.profitRaw - Math.min(item.sellPrice * 0.01, 5000000)
                                    )}
                                </TableCell>
                                <TableCell>{item.profitPercent.toFixed(2)}%</TableCell>
                            </StyledTableRow>
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
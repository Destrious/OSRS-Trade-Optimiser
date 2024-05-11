import React from 'react';
import { AppBar, Box, Button, Toolbar } from '@mui/material';
import logo from "../logo.svg";

const Navbar = () => {
    return (
        <AppBar position="static" sx={{ bgcolor: '#d7ba79' }}>
            <Toolbar>

                <Box component="div">
                    <img src={logo} className="0SRS-L0G0" alt="logo" style={{ width: '100px', height: '100px' }} />
                </Box>

                {/* flexGrow={1} to take up max width */}
                <Box display="flex" flexGrow={1}>
                    <Button color="inherit" href="/">
                        Home
                    </Button>
                </Box>

                <Button color="inherit" href="/login">
                    Log in
                </Button>
            </Toolbar>

        </AppBar>
    );
};

export default Navbar;

// ---------- styling


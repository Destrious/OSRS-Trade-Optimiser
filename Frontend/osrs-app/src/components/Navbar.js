import React from 'react';
import {AppBar, Box, Button, styled, Toolbar} from '@mui/material';
import logo from "../OSRS_logo.png";

const Navbar = () => {
    return (
        // <AppBar position="sticky" sx={{ bgcolor: '#d7ba79' }}>
        <AppBar position="sticky" sx={{ bgcolor: '#C0A886' }}>
            <Toolbar>

                {/* logo go in this box*/}
                <Box component="div" style={{ width: '80px' }}>
                    <img src={logo} className="0SRS-L0G0" alt="logo" style={{ width: '65px', height: '60px', padding: '10px' }} />
                </Box>

                {/* flexGrow={1} to take up max width */}
                {/* left side buttons go in this box */}
                <Box display="flex" flexGrow={1}>
                    <Button color="inherit" href="/">
                        Home
                    </Button>
                    <Button color="inherit" href="/trade">
                        Trade
                    </Button>
                </Box>

                {/* right side buttons go here */}
                <Button color="inherit" href="/login">
                    Log in
                </Button>
            </Toolbar>

        </AppBar>
    );
};

export default Navbar;
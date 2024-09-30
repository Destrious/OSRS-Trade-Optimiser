import React from 'react';
import { Checkbox, FormControlLabel } from '@mui/material';

const SearchFilterCheckbox = ({ name, checked, onChange }) => {
    return (
        <FormControlLabel
            control={
                <Checkbox
                    checked={checked}
                    onChange={onChange}
                    inputProps={{ 'aria-label': 'controlled' }}
                />
            }
            style={{ marginLeft: '10px', marginTop: '10px' }}
            label={name}
        />
    );
};

export default SearchFilterCheckbox;
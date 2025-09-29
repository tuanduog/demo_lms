import * as React from 'react';
// material-ui
import { useTheme } from '@mui/material/styles';
import { FormControl, InputLabel, MenuItem, OutlinedInput, Select } from '@mui/material';

export default function FilterSelect({ name, names, currentValue, handleChange }) {
  const theme = useTheme();
  const ITEM_HEIGHT = 48;
  const ITEM_PADDING_TOP = 8;
  const MenuProps = {
    PaperProps: {
      style: {
        maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
        width: 150
      }
    }
  };
  const getStyles = (item, personName, theme) => {
    return {
      fontWeight: personName.includes(item) ? theme.typography.fontWeightMedium : theme.typography.fontWeightRegular
    };
  };
  return (
    <>
      <FormControl sx={{ m: 1, width: 150 }}>
        <InputLabel id="demo-multiple-name-label">{name}</InputLabel>
        <Select
          labelId="demo-multiple-name-label"
          id="demo-multiple-name"
          multiple
          value={currentValue}
          onChange={handleChange}
          input={<OutlinedInput label="Name" />}
          MenuProps={MenuProps}
          onClose={(e) => {
            e.target.blur();
          }}
        >
          {names.map((item) => (
            <MenuItem key={item} value={item} style={getStyles(item, currentValue, theme)}>
              {item}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
    </>
  );
}

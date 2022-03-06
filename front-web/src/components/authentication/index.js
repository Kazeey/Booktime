import React from 'react'
import { Box } from '@mui/system'
import PropTypes from 'prop-types'
import { FormControl, IconButton, Input, InputAdornment, InputLabel, TextField } from '@mui/material';
import EmailIcon from '@mui/icons-material/Email';
import { Visibility, VisibilityOff } from '@mui/icons-material';

const style = {
  width : 400,
  bgcolor : 'white',
  border : "1px solid #707070",
  p : 2,
  px : 4,
  pb : 3,
};

const Authentication = ( props ) => {

  const [values, setValues] = React.useState({
    email: '',
    password: '',
    showPassword: false,
  });

  const handleChange = (prop) => (event) => {
    setValues({ ...values, [prop]: event.target.value });
  };

  const handleClickShowPassword = () => {
    setValues({
      ...values,
      showPassword: !values.showPassword,
    });
  };

  const handleMouseDownPassword = (event) => {
    event.preventDefault();
  };

  return (
    <Box sx={style}>
      <FormControl>
        <Box sx={{ display: 'flex', alignItems: 'flex-end' }}>
          <EmailIcon sx={{ color: 'action.active', mr: 1, my: 0.5 }} />
          <TextField id="mailInput" label="Adresse mail" variant="standard" onChange={handleChange('email')} />
        </Box>
      </FormControl>

      <FormControl>
        <Box sx={{ display: 'flex', alignItems: 'flex-end' }}>
        <InputLabel htmlFor="standard-adornment-password">Password</InputLabel>
          <Input
            id="standard-adornment-password"
            type={values.showPassword ? 'text' : 'password'}
            value={values.password}
            onChange={handleChange('password')}
            endAdornment={
              <InputAdornment position="end">
                <IconButton
                  aria-label="toggle password visibility"
                  onClick={handleClickShowPassword}
                  onMouseDown={handleMouseDownPassword}
                >
                  {values.showPassword ? <VisibilityOff /> : <Visibility />}
                </IconButton>
              </InputAdornment>
            }
          />
        </Box>
      </FormControl>
    </Box>
  )
}

Authentication.propTypes = {
  email : PropTypes.string,
  password : PropTypes.string
}

export default Authentication
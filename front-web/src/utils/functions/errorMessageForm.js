import { FormHelperText } from "@mui/material";

/**
* @param {String} error
* @returns {FormHelperText}
*
* @description
* Display the error message
* If the error is empty, display nothing
*/
const errorMessage = (error) => {
    return (
        <FormHelperText error>
            {error}
        </FormHelperText>
    )
}

export default errorMessage;
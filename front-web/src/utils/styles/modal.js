import { ModalUnstyled } from '@mui/base'
import { styled } from '@mui/system'

const styleModal = {
    StyledModal : styled(ModalUnstyled)`
        position: fixed;
        z-index: 1300;
        right : 0;
        bottom : 0;
        left : 0;
        top : 0;
        display: flex;
        align-items: center;
        justify-content: center;
    `,

    Backdrop : styled(`div`)`
        z-index: -1;
        position: fixed;
        right : 0;
        bottom : 0;
        left : 0;
        top : 0;
        background-color: rgba(0,0,0,0.2);
        -webkit-tap-highlight-color: transparent;
    `,
}

export default styleModal;
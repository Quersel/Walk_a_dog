import * as UT from './userTypes';
import axios from 'axios';

const REGISTER_URL = 'http://localhost:8080/wad/api/v1/users/registration';

export const registerUser = (userObj) => async (dispatch) => {
    dispatch(userRequest())
    try {
        const response = await axios.post(REGISTER_URL, userObj)
        dispatch(userSavedSuccess(response.data))
        return Promise.resolve(response.data)
    } catch(error) {
        dispatch(userFailure(error.message))
        return Promise.reject(error)
    }
}

const userRequest = () => {
    return {
        type: UT.USER_REQUEST
    }
}

const userSavedSuccess = (user) => {
    return {
        type: UT.USER_SAVED_SUCCESS,
        payload: user
    }
}

const userFailure = error => {
    return {
        type: UT.USER_FAILURE,
        payload: error
    }
}
import axios from "axios"
import { IUser } from "./models/IUser"

const baseURL = 'https://www.jsonbulut.com/json/'
const ref = '74430d47fa16b4c53c0fe59510752c70'
const config = axios.create({
    baseURL: baseURL,
    params: {
        ref: ref
    }
})


export const userLogin = (email:string, password: string) => {
    const sendParams = {
        userEmail: email,
        userPass: password,
        face: 'no'
    }
    // body json object send service
    //return config.post('userLogin.php', sendParams )
    //return config.post('userLogin.php', null, { data: sendParams } )
    
    // query string send data 
    return config.get<IUser>('userLogin.php', { params: sendParams }  )
}
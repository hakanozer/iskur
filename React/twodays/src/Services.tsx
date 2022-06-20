import axios from "axios"
import { IUser } from "./models/IUser"
import { IUserRegister } from "./models/IUserRegister"

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

export const userRegister = (name: string, surname: string, phone: string, email: string, password: string) => { //yazdığımız özelliği dışarda görmek için kullanırız.                     
    //bu fonk çalıştığında kullanıcının girdiği mail ve password almak için logine gidecek


    //servis getse get postsa post 
    const sendParams = { //bura postmanin içi 
        userName: name,
        userSurname: surname,
        userPhone: phone,
        userMail: email,
        userPass: password


    }

    //body json object send service
    //return config.post('userLogin.php',sendParams )
    // return config.post('userLogin.php',null ,{ data: sendParams }) //bu body yollarken  post//null header

    //querystring send data
    return config.get<IUserRegister>('userRegister.php', { params: sendParams })

}      
import React from 'react'
import { Navigate } from 'react-router-dom'
import NavBar from './components/NavBar'
import { Bilgiler } from './models/IUser'

function Security( item: { component: JSX.Element } ) {

  
   const control = () => {
    const stSession = sessionStorage.getItem("user")
    if ( stSession ) {
        try {
            const bilgiler:Bilgiler = JSON.parse( stSession )
            return bilgiler
        } catch (error) {
            sessionStorage.removeItem('user')
            return null
        }

    }else {
        return null
    }
   }
    const bilControl = control();

  return (
    bilControl === null 
    ? 
        <Navigate to='/' />
    :
       <> <NavBar bilgi={bilControl} /> {item.component} </> 
  )
}

export default Security
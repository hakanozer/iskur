import React from 'react'
import { Navigate } from 'react-router-dom'
import { Bilgiler } from './models/IUser'

function Security( item: { component: JSX.Element } ) {

  
   const control = () => {
    const stSession = sessionStorage.getItem("user")
    if ( stSession ) {
        const bilgiler:Bilgiler = JSON.parse( stSession )
        return bilgiler
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
        item.component
  )
}

export default Security
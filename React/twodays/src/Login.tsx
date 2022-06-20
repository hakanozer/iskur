import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { userLogin } from './Services';


function App() {

  // sayfa geçiş hareketleri için kullanılan bir yöntemdir.
  const navigate = useNavigate()

  // useState
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [loginError, setLoginError] = useState(false)
  const [loginMessage, setLoginMessage] = useState('')
  // arrow function
  const fncSend = (evt:React.FormEvent) => {
    evt.preventDefault()
    userLogin(email, password).then( res => {
      const user = res.data.user[0]
      const durum = user.durum
      const mesaj = user.mesaj
      setLoginMessage( mesaj )
      if ( durum ) {
        const stData = JSON.stringify( user.bilgiler! )
        sessionStorage.setItem("user", stData)
        navigate('/dashboard')
      }else {
        //alert( mesaj )
        setLoginError(true)
      }
    })
  }


  return (
    <>
      <div className='row'>
        <div className='col-sm-4'></div>
        <div className='col-sm-4'>
          <h1>User Login</h1>
          
            <div style={{ display: loginError === true ? 'block' : 'none'  }} className="alert alert-danger alert-dismissible fade show" role="alert">
              <strong>Error!</strong> { loginMessage }
              <button type="button" className="btn-close" aria-label="Close" onClick={(evt) => setLoginError(false)}  ></button>
            </div>
          
          <form onSubmit={fncSend}>
            <div className='mt-3'>
              <input onChange={(evt) => setEmail(evt.target.value) } type='email' className='form-control' placeholder='E-Mail' />
            </div>
            <div className='mt-3'>
              <input onChange={(evt) => setPassword(evt.target.value) }  type='password' className='form-control' placeholder='Password' />
            </div>
            <div className='mt-3'>
              <button className='btn btn-success' type='submit'>Send</button>
              <a onClick={(evt) => navigate('/register') } role='button' className='btn btn-danger'>Register</a>
            </div>
          </form>
        </div>
        <div className='col-sm-4'></div>
      </div>
    </>
  )
}

export default App;

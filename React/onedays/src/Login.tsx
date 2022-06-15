import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';


function App() {

  // sayfa geçiş hareketleri için kullanılan bir yöntemdir.
  const navigate = useNavigate()

  // useState
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  // arrow function
  const fncSend = (evt:React.FormEvent) => {
    evt.preventDefault()
    console.log("fncSend Call", email, password)
  }

  return (
    <>
      <div className='row'>
        <div className='col-sm-4'></div>
        <div className='col-sm-4'>
          <h1>User Login</h1>
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

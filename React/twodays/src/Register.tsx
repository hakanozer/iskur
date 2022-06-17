import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';


function Register() {


  const navigate= useNavigate()
  //useState
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [name, setName] = useState('')
  const [surname, setSurname] = useState('')
  const [city, setCity] = useState('')

  // useEffect
  useEffect(() => {
    console.log('useEffect call-1')
  }, [])

  useEffect(() => {
    if ( name.length > 2 ) {
      console.log('useEffect call-2', name)
    }
  }, [name])
  


  //arrow function
  const fncRegister=(evt:React.FormEvent)=>{
    evt.preventDefault()
    console.log( city )
  }

  // array
  const arr:string[] = ['İstanbul', 'İzmir', 'Antalya', 'Çeşme', 'Muğla', 'Balıkesir', 'Ordu']
  



  return (
  <>
  {/*yorum satırı olarak yaparsın return içiyse */}
    <div className='row'>
      <div className='col-sm-4'> </div>
      <div className='col-sm-4'>
        <h1>User Register</h1>
        <form onSubmit={fncRegister} >
          <div className='mt-3'><input onChange={(evt)=> setName(evt.target.value)}  className='form-control' placeholder='Input Name Please'/></div>
          <div className='mt-3'><input onChange={(evt)=> setSurname(evt.target.value)}  className='form-control' placeholder='Input Surname Please'/></div>
          <div className='mt-3'><input onChange={(evt)=> setEmail(evt.target.value)} type='email' className='form-control' placeholder='E-Mail'/></div>
          <div className='mt-3'><input  onChange={(evt)=> setPassword(evt.target.value)} type={'password'} className='form-control' placeholder='Password'/></div>
          
          <div className='mt-3'>
            <select required onChange={(evt) => setCity( evt.target.value )}  className="form-select" aria-label="Default select example">
              <option hidden value='' >Selected</option>
              { arr.map( (item, index) =>
                <option key={index} value={item}> { item } </option>
              )}
            </select> 
          </div>
          
          <div className='mt-3'>
          <button type='submit' className='btn btn-primary'>Send</button>
          <a  onClick={(evt)=>navigate('/')} className='btn btn-danger' role='button'>Login</a>
          </div>
          
        </form>
         </div>
      <div className='col-sm-4'> </div>
    </div>
  </>
  )
}

export default Register ;
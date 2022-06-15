import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Routes, Route } from 'react-router-dom'

// import components
import Login from './Login';
import Register from './Register';

const appRoutes =
<BrowserRouter>
  <Routes>
    <Route path='/' element={ <Login /> } />
    <Route path='/register' element={ <Register/> } />
  </Routes>
</BrowserRouter> 


const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render( appRoutes );
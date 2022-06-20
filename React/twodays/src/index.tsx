import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter, Routes, Route } from 'react-router-dom'


// import components
import Login from './Login';
import Register from './Register';
import Dashboard from './Dashboard';
import Security from './Security';
import Settings from './Settings';

const appRoutes =
<BrowserRouter>
  <Routes>
    <Route path='/' element={ <Login /> } />
    <Route path='/register' element={ <Register/> } />
    <Route path='/dashboard' element={ <Security component={<Dashboard />} /> }></Route>
    <Route path='/settings' element={ <Security component={<Settings />} /> }></Route>
  </Routes>
</BrowserRouter> 


const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render( appRoutes );
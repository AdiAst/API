import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import CarList from "./CarList"
import reportWebVitals from './reportWebVitals';
import "bootstrap/dist/css/bootstrap.min.css";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <div className='container'>
    <h1>XYZ Cars </h1>
    <CarList/>
  </div>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

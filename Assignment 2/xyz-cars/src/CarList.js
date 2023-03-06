import React,{ useState, useEffect } from 'react';
import Service from './Service';
import "bootstrap/dist/css/bootstrap.min.css";


class CarList extends React.Component{
    constructor(){
        super()
        this.state={
            cars:[]
        }
    }
    componentDidMount(){
        Service.getCars().then(cars=>{
            this.setState({cars: cars.data})
        });
        
    }

    render(){
        return(
            
        <table className='table table-hocer table-dark'>
            <thead>
            <tr>
                <th>Make</th>
                <th>Model</th>
                <th>Registration</th>
                <th>Price</th>
            </tr>
            </thead>
            <tbody>
            {this.state.cars.map((car) =>(
                <tr key={car.id}>
                <td>{car.make}</td>
                <td>{car.model}</td>
                <td>{car.registration}</td>
                <td>{car.price}</td>           
            </tr>
                ))}
            </tbody>
        </table>
        );
    }
}



export default CarList;

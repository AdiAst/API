import React from "react";
import "bootstrap/dist/css/bootstrap.min.css"
import { useState } from "react";
import Service from "../Service"

class Webhook extends React.Component{
  constructor(){
    super()
    this.state={
        text:""
    }
    this.sendMSG = this.sendMSG.bind(this);
  }
  sendMSG(){
    const data =  this.state.text
    
    Service.sendMessage(data).then(response => {console.log(response)}).catch(e=>{console.log(e)})
  }

  render(){
    return(
        <>
        <h1>Send Message</h1>
        <form onSubmit={this.sendMSG}>
            <label htmlFor="text">Message :</label>
            <input type="text" id="text" className="form-control" onChange={(x) => this.setState({text:x.target.value})} />
            <button type="submit">SEND</button>
        </form>
        </>
    )
  }
}
export default Webhook
import CarList from "../Component/Carlist";

const Home = () =>{
    
    const user = JSON.parse(sessionStorage.getItem('user'))
    console.log(user)
    return (
        <>
        <h1>XYZ Cars List</h1>
        {user ? 
        <h2>Hello {user.data.name}</h2>
        : ""}
        <CarList/>
        </>
    )   
}

export default Home
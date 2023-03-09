import {BrowserRouter,Routes,Route} from 'react-router-dom'
import './App.css'
import Home from './Pages/Home'
import Login from './Pages/Login'
import Navbar from './Component/Navbar'
import Webhook from './Pages/Webhook'

function App() {
  return (
    <>
    <Navbar/>
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Home/>}/>
        <Route path='/login' element={<Login/>}/>
        <Route path='/send-message' element={<Webhook/>}/>
      </Routes>
    </BrowserRouter>
    </>
  )

}

export default App;

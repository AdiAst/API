import "bootstrap/dist/css/bootstrap.min.css"

function Navbar(){
    const user = JSON.parse(sessionStorage.getItem('user'))
    function logout(){
        sessionStorage.clear()
        window.location.href="/"
    }
    return(
    <nav className="navbar navbar-expand-lg bg-light mb-5">
        <div className="container-fluid">
            <a className="navbar-brand fw-bold" href="#">XYZ Cars</a>
            <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                <li className="nav-item">
                <a className="nav-link fw-bold" href="/">Home</a>
                </li>
                <li className="nav-item">
                <a className="nav-link fw-bold" href="/send-message">Send Message</a>
                </li>
                {!user ?
                <li className="nav-item">
                <a className="nav-link fw-bold" href="/login">Login</a>
                </li>
                :
                <li className="nav-item">
                <button className="nav-link fw-bold border-0 " onClick={() => logout()}>
                    Logout</button>
                </li>
                }
                
            </ul>
            <form className="d-flex" role="search">
                <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
                <button className="btn btn-outline-success" type="submit">Search</button>
            </form>
            </div>
        </div>
    </nav>
    )
}
export default Navbar
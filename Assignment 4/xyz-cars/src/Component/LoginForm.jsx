import "bootstrap/dist/css/bootstrap.min.css"
import { LoginSocialFacebook } from "reactjs-social-login"
import { FacebookLoginButton } from "react-social-login-buttons"


function LoginForm(){
    return(
    <div className="card text-bg-dark mb-3 w-50 m-auto">
        <div className="card-header fs-2">Login Form</div>
        <div className="card-body">
        <form method="post" action="/">
        <div className="mb-3">
            <label htmlFor="username" className="form-label">Username</label>
            <input type="text" className="form-control" name="username" id="username"  />
        </div>
        <div className="mb-3">
            <label htmlFor="password" className="form-label">Password</label>
            <input type="password" className="form-control" name="password" id="password" />
        </div>
        <button type="submit" className="btn btn-primary">Submit</button>
        </form>
        <span className="text-center my-3">or</span>
        <LoginSocialFacebook appId="1236375246987656" 
        onResolve={
            (response)=> {
                console.log(response)
                sessionStorage.setItem('user',JSON.stringify(response))
                window.location.href="/"
            }
        }
        onReject={(error)=>{console.log(error)}}
        >
            <FacebookLoginButton/>
        </LoginSocialFacebook>
        </div>
    </div>
    
    )
}
export default LoginForm
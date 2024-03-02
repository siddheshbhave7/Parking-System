import { Link, useNavigate } from 'react-router-dom'

// for getting the current state of signin status
import { useSelector, useDispatch } from 'react-redux'

const Navbar = () => {
  // get the current status
  const signinStatus = useSelector((state) => state.authSlice.status)

  // get the dispatcher
  const dispatch = useDispatch()

  // used to navigate
  const navigate = useNavigate()

  return (
   <>
   <nav class="navbar navbar-expand-lg navbar-light bg-light" style={{backgroundColor:'#9bb5b9'}}>
  <div class="container-fluid">
    <Link class="navbar-brand" to='/home'>Park Safe</Link>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <div class="navbar-nav">
        <Link class="nav-link active" aria-current="page" to='/home'>Home</Link>
        <Link class="nav-link" to='/signin'>Login</Link>
        <Link class="nav-link" to='/signup'>Signup</Link>
      </div>
    </div>
  </div>
</nav>
   </>
  )
}

export default Navbar

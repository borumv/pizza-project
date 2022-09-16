
import React from 'react'
import { Route, Routes } from 'react-router-dom';

import './scss/app.scss';
import Header from './components/Header'
import Home from './pages/Home'
import NotFound from './pages/NotFound';
import Cart from './pages/Cart'
import { useSelector, useDispatch } from 'react-redux'
import { decrement, increment } from './redux/slices/counterSlice'

export const SearchContext = React.createContext('')

function App() {

  const [searchValue, setSearchValue] = React.useState('')
  const count = useSelector((state) => state.countReducer.value)
  const dispatch = useDispatch()
  

  return (

    <div className="App">
      <div className="wrapper">
        <SearchContext.Provider value={{searchValue, setSearchValue}}>
        <Header />
        <div className="content">
          <div className="container">
            <Routes>
              <Route path='/' element={<Home/>}/>
              <Route path='/cart' element ={<Cart/>}/>
              <Route path='*' element={<NotFound/>}/>
            </Routes>
          </div>
        </div>
        </SearchContext.Provider>
      </div>
      {/* <div>
        <button
          aria-label="Increment value"
          onClick={() => dispatch(increment())}
        >
          Increment
        </button>
        <span>{count}
        heeellol</span>
        <button
          aria-label="Decrement value"
        
          onClick={() => dispatch(decrement())}
        >
          Decrement
        </button>
      </div> */}
    </div>
  );
}

export default App;

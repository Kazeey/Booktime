import React, { Fragment, Suspense } from 'react'
import { Route, Routes } from 'react-router-dom';
import './App.css';

import HomeContainer from './containers/HomeContainer'
import BookContainer from './containers/BookContainer'
import AuthorContainer from './containers/AuthorContainer'
import SettingsContainer from './containers/SettingsContainer'


const App = () => {
  return (
    <div className='App'>
      <Fragment>
        <Suspense fallback={<div>Loading...</div>}>
          <Routes>
            <Route path="/" element={<HomeContainer />} />
            <Route path="/books" element={<BookContainer />} />
            <Route path="/authors" element={<AuthorContainer />} />
            <Route path="/settings" element={<SettingsContainer />} />
          </Routes>
        </Suspense>
      </Fragment>
    </div>
  )
}

export default App






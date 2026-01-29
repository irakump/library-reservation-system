import './App.css';
import Home from './pages/Home.jsx';
import { Routes, Route } from 'react-router';

function App() {
  return (
    <>
      <main></main>
      <Routes>
        <Route path="/" element={<Home />} />
      </Routes>
    </>
  );
}

export default App;

import React from "react";
import "./App.css";
import Header from "./components/Layout/Header";
import Landing from "./components/Layout/Landing";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import NotFound from "./components/Layout/NotFound";
import Dashboard from "./components/CapabilityTool/Dashboard";
import AddCapability from "./components/CapabilityTool/AddCapability";
import UpdateCapability from "./components/CapabilityTool/UpdateCapability";
import { Provider } from "react-redux";
import store from "./store";

const App = () => {
  return (
      <Provider store={store}>
        <Router>
          <div className="App">
            <Header />
            <div className="container">
              <Routes>
                <Route exact path="/" element={<Landing />} />
                <Route exact path="/dashboard" element={<Dashboard />} />
                <Route exact path="/addCapability" element={<AddCapability />} />
                <Route exact path="/updateCapability" element={<UpdateCapability />}/>
                <Route path="*" element={<NotFound />} />
              </Routes>
            </div>
          </div>
        </Router>
      </Provider>
  );
}

export default App;

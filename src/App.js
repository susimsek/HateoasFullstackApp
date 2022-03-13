import React, {Suspense} from "react";
import Header from "./components/Layout/Header";
import Landing from "./components/Layout/Landing";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import PageNotFound from "./components/Layout/PageNotFound";
import Dashboard from "./components/CapabilityTool/Dashboard";
import AddCapability from "./components/CapabilityTool/AddCapability";
import UpdateCapability from "./components/CapabilityTool/UpdateCapability";
import { Provider } from "react-redux";
import store from "./store";
import {Spinner} from "react-bootstrap";


const Page = () => {
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
                <Route path="*" element={<PageNotFound />} />
              </Routes>
            </div>
          </div>
        </Router>
      </Provider>
  );
}

const Loader = () => (
    <div className="d-flex justify-content-center">
      <Spinner animation="border" role="status">
        <span className="visually-hidden">Loading...</span>
      </Spinner>
    </div>
);

const App = () => {
    return (
        <Suspense fallback={<Loader />}>
            <Page />
        </Suspense>
    );
}

export default App;

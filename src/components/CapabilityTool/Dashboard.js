import React, {useEffect} from "react";
import AddButton from "./AddButton";
import Capability from "./Capability";
import {useDispatch, useSelector} from "react-redux";
import {deleteCapabilityHandler, getAllCapabilitiesHandler} from "../../actions/CapabilityActions";
import {useApiProgress} from "../../shared/ApiProgress";
import {Card, Pagination, Spinner} from "react-bootstrap";

const Dashboard = () => {

    const dispatch = useDispatch();


    const { capabilities, page, links } = useSelector((store) => ({
        capabilities: store.capability.capabilities,
        page: store.capability.page,
        links: store.capability.links
    }));

    const pendingApiCall = useApiProgress('get','/capabilities', false);

    useEffect(() => {
        dispatch(getAllCapabilitiesHandler());
    }, [dispatch]);

    const onClickPrev = () => {
        dispatch(getAllCapabilitiesHandler(links.prev.href));
    }

    const onClickNext = () => {
        dispatch(getAllCapabilitiesHandler(links.next.href));
    }

    const {totalPages} = page;

    return (
        <>
            <AddButton />
            { pendingApiCall && <div className="d-flex justify-content-center">
                <Spinner animation="border" role="status">
                <span className="visually-hidden">Loading...</span>
                </Spinner>
                </div>
            }
            {
                capabilities.map(capability => (
                    <Capability key={capability.id} capability={capability} />
                ))
            }

            {totalPages !== 0 &&
            <Card border="primary" className="mb-3">
                <Pagination border="primary" className="d-flex justify-content-center mt-3">
                    <Pagination.Prev onClick={onClickPrev} disabled={!links.prev}/>
                    <Pagination.Next onClick={onClickNext} disabled={!links.next} />
                </Pagination>
            </Card>}
        </>
    );
}

export default Dashboard;

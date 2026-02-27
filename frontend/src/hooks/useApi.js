import {useEffect, useState} from "react";



export function useApi(endpoint) {
    const URL = "http://localhost:8081/api"
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [data, setData] = useState([]);

    useEffect(() => {
        async function fetchData() {
            try {
                const response = await fetch(`${URL}${endpoint}`);
                if (!response.ok) throw new Error(`HTTP error: ${response.status}`)
                const json = await response.json();
                console.log("data: ", json);
                setData(json);
            } catch (error) {
                setError(error);
                console.log(error);
            } finally {
                setLoading(false);
            }
        }
        fetchData();
    }, [endpoint]);


    return {data, loading, error};
}
import './App.css'
import  {FormEvent, useEffect, useState} from "react";

const endpoint = import.meta.env.VITE_API_URL + '/api/messages';

type Message = {
    content: string
}

const INITIAL_MESSAGE: Message = {
    content: ''
}

const App = () => {

    const [message, setMessage] = useState<Message>(INITIAL_MESSAGE)

    const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        const content = data.get('message');
        const response = await fetch(endpoint, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({content})
        });
        const receivedMessage: Message = await response.json();
        setMessage(receivedMessage);
    }
    useEffect(() => {
        const fetchLastMessage = async () => {
            const response = await fetch(endpoint + '/last');
            const receivedMessage: Message = await response.json();
            setMessage(receivedMessage);
        };

        fetchLastMessage();
    }, []);

    return (
        <article>
            <section>
                <form onSubmit={handleSubmit}>
                    <fieldset>
                        <legend>Send a message</legend>
                        <label htmlFor="message">Content:</label>
                        <input type="text" name="message" id="message"/>
                        <button type="submit">Send</button>
                    </fieldset>
                </form>
            </section>

            <section>
                <h3>Last Submitted Message</h3>
                <p>{message.content}</p>
            </section>
        </article>
    )
};

export default App
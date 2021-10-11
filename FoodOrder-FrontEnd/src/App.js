import { Layout, Typography } from "antd";
import { useState } from "react";
import LoginForm from "./components/LoginForm";
import FoodList from "./components/FoodList";
import MyCart from "./components/MyCart"
import Footer from "./components/Footer"
import SignupForm from "./components/SignupForm";

const { Header, Content } = Layout;
const { Title } = Typography;

function App() {
    const [authed, setAuthed] = useState(false);

    return (
        <Layout style={{ height: "100vh" }}>
            <Header>
                <div className="header"
                     style={{display: "flex",
                         justifyContent: "space-between"}}>
                    <Title
                        level={2}
                        style={{ color: "white",
                            lineHeight: "inherit",
                            marginBottom: 0 }}
                    >
                        DEMO: Online Food Ordering System
                    </Title>
                    {/*/!*If authentication is successful, enter the cart page*!/*/}
                    <div>{authed ? <MyCart /> : <SignupForm />}</div>
                </div>
            </Header>
            <Content
                style={{
                    padding: "50px",
                    maxHeight: "calc(100% - 64px)",
                    overflowY: "auto",
                }}
            >
                {authed ? (
                    <FoodList />
                ) : (
                    <LoginForm onSuccess={() => setAuthed(true)} />
                )}
            </Content>
            <Footer />
        </Layout>
    );
}

export default App;

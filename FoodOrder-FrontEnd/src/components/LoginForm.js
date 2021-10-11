import { Button, Form, Input, message } from "antd";
// import {useRef, useEffect} from "react";
import React from "react";
import { LockOutlined, UserOutlined } from "@ant-design/icons";
import { login } from "../utils";

//step1: set loading true
//2: send login request(call login api) to the server
//3:deal with login status: app logged in or not
//4:set loading false
class LoginForm extends React.Component {
    state = {
        loading: false
    }

    onFinish = (values) => {
        // step1: set loading true
        // step2: send login request( call login api ) to the server
        // step3: deal with login status -> logged in or not
        // step4: set loading false
        this.setState({ loading: true });
        login(values)
            .then( () => {
                // show logged in
                message.success('Login Successful');
                this.props.onSuccess();
            })
            .catch( err => {
                // show err
                message.error(err.message );
            })
            .finally( () => {
                this.setState({ loading: false })
            })
    };

    render() {
        return (
            <Form
                name="normal_login"
                className="login-form"
                onFinish={this.onFinish}
            >
                <Form.Item
                    name="username"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your Username!',
                        }
                    ]}
                >
                    <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Username" />
                </Form.Item>
                <Form.Item
                    name="password"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your Password!',
                        },
                    ]}
                >
                    <Input.Password
                        prefix={<LockOutlined />}
                        placeholder="Password"
                    />
                </Form.Item>

                <Form.Item>
                    <Button
                        type="primary"
                        htmlType="submit"
                        loading={ this.state.loading }
                    >
                        Log in
                    </Button>
                </Form.Item>
            </Form>
        );
    }
}

export default LoginForm;
import React, {useState, useEffect} from 'react';
import { Select, message, List, Card, Tooltip, Button } from 'antd';
import { getRestaurants, getMenus, addItemToCart } from '../utils';
import { PlusOutlined } from '@ant-design/icons';

const { Option } = Select;

function AddToCartButton({itemId}) {
    const [loading, setLoading] = useState(false);

    const AddToCart = () => {
        // add selected menu to the cart
        // step1: set loading to true
        // step2: add menu to cart and inform the server
        setLoading(true);
        addItemToCart(itemId)
            .then(() => message.success(`Successfully add item`))
            .catch((err) => message.error(err.message))
            .finally(() => {
                setLoading(false);
            });

    }
    return (
        <Tooltip title="Add to shopping cart">
            <Button
                loading={loading}
                type="primary"
                icon={<PlusOutlined />}
                onClick={AddToCart}
            />
        </Tooltip>
    )
}

function FoodList(props) {
    // current selected option
    const [curRest, setCurRest] = useState();
    // loading restaurant status
    const [loadingRest, setLoadingRest] = useState(false);
    // restaurant list
    const [restaurants, setRestaurants] = useState([]);
    // loading restaurant menu
    const [loading, setLoading] = useState(false);
    // store menu status
    const [foodData, setFoodData] = useState([]);

    // fetch restaurant list
    useEffect(() => {
        // step1: set loading restaurant = true
        // step2: fetch restaurant list from the server
        setLoadingRest(true);
        getRestaurants()
            .then( response => {
                setRestaurants(response);
            })
            .catch( err => {
                message.error(err.message);
            })
            .finally( () => {
                setLoadingRest(false);
            })
    }, []);

    // fetch menu of current selected restaurant
    useEffect( () => {
        // step1: set loading menu status
        // step2: fetch menu from the server
        if(curRest) {
            setLoading(true);
            getMenus(curRest)
                .then( response => {
                    setFoodData(response);
                })
                .catch( err => {
                    message.error(err.message);
                })
                .finally( () => {
                    setLoading(false);
                })
        }
    }, [curRest])

    return (
        <>
            <Select
                value={curRest}
                loading={loadingRest}
                style={{ width: 300 }}
                placeholder="Select a restaurant"
                onChange={ () => {} }
                onSelect={ value => setCurRest(value) }
            >
                {
                    restaurants.map(
                        item => <Option key={item.id}
                                        value={item.id}>
                            {item.name}
                        </Option>
                    )
                }
            </Select>
            {
                curRest && (
                    <List
                        style={{ marginTop: 20 }}
                        loading={ loading }
                        grid={{
                            gutter: 16,
                            xs: 1,
                            sm: 2,
                            md: 4,
                            lg: 4,
                            xl: 3,
                            xxl: 3,
                        }}
                        dataSource={foodData}
                        renderItem={item => (
                            <List.Item>
                                <Card title={item.name}
                                      extra={<AddToCartButton itemId={item.id}/>}
                                >
                                    <img src={item.imageUrl}
                                         alt={item.name}
                                         style={{ height: 'auto',
                                             width: "100%",
                                             display: "block" }}
                                    />
                                    Price: { item.price }
                                </Card>
                            </List.Item>
                        )}
                    />
                )
            }
        </>
    );
}

export default FoodList;
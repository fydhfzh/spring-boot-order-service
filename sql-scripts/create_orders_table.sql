DROP TABLE IF EXISTS orders;

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    quantity INTEGER NOT NULL,
    total_price INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    constraint fk_user_orders
        foreign key (user_id) references users(id),
    constraint fk_product_orders
        foreign key (product_id) references products(id)
);
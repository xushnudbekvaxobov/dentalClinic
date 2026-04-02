insert into users (
    id,
    email,
    password,
    role,
    status,
    is_account_non_expired,
    is_account_non_locked,
    is_credentials_non_expired,
    is_enabled,
    created_at,
    updated_at
) values (
             gen_random_uuid(),
             'xushnudbekvakhobov@gmail.com',
             '$2a$10$9KCuEhLxySqMbgZ3O0qpne2rIeLy6GAppzobu4/uzAZW3o3hntQg6',
             'ADMIN',
             'ACTIVE',
             true,
             true,
             true,
             true,
             now(),
             now()
         );
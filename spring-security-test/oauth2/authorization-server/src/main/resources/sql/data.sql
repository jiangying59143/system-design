INSERT INTO
    `oauth-server`.`oauth2_registered_client` (`id`,
                                               `client_id`,
                                               `client_id_issued_at`,
                                               `client_secret`,
                                               `client_secret_expires_at`,
                                               `client_name`,
                                               `client_authentication_methods`,
                                               `authorization_grant_types`,
                                               `redirect_uris`,
                                               `post_logout_redirect_uris`,
                                               `scopes`, `client_settings`,
                                               `token_settings`)
VALUES ('3eacac0e-0de9-4727-9a64-6bdd4be2ee1f',
        'oidc-client', '2023-07-12 07:33:42',
        '$2a$10$.J0Rfg7y2Mu8AN8Dk2vL.eBFa9NGbOYCPOAFEw.QhgGLVXjO7eFDC',
        NULL, '3eacac0e-0de9-4727-9a64-6bdd4be2ee1f',
        'client_secret_basic', 'refresh_token,authorization_code',
        'http://127.0.0.1:9100/login/oauth2/code/oidc-client',
        'http://127.0.0.1:9000/',
        'openid,profile',
        '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}', '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",3600.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.device-code-time-to-live\":[\"java.time.Duration\",300.000000000]}');

INSERT INTO `oauth-server`.`oauth2_registered_client` (`id`,
                                                       `client_id`,
                                                       `client_id_issued_at`,
                                                       `client_secret`,
                                                       `client_secret_expires_at`,
                                                       `client_name`,
                                                       `client_authentication_methods`,
                                                       `authorization_grant_types`,
                                                       `redirect_uris`,
                                                       `post_logout_redirect_uris`,
                                                       `scopes`, `client_settings`,
                                                       `token_settings`)
VALUES ('3eacac0e-0de9-4727-9a64-6bdd4be2ee3',
        'web-client-id-order',
        '2023-07-12 07:33:42',
        '$2a$10$.J0Rfg7y2Mu8AN8Dk2vL.eBFa9NGbOYCPOAFEw.QhgGLVXjO7eFDC',
        NULL,
        'web平台-SSO客户端-订单服务',
        'client_secret_basic',
        'refresh_token,authorization_code',
        'http://127.0.0.1:9001/login/oauth2/code/order-client',
        'http://127.0.0.1:9000/',
        'openid,profile',
        '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}',
        '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",1800.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",3600.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.device-code-time-to-live\":[\"java.time.Duration\",300.000000000]}');


INSERT INTO `oauth-server`.`oauth2_registered_client` (`id`,
                                                       `client_id`,
                                                       `client_id_issued_at`,
                                                       `client_secret`,
                                                       `client_secret_expires_at`,
                                                       `client_name`,
                                                       `client_authentication_methods`,
                                                       `authorization_grant_types`,
                                                       `redirect_uris`,
                                                       `post_logout_redirect_uris`,
                                                       `scopes`, `client_settings`,
                                                       `token_settings`)
VALUES ('3eacac0e-0de9-4727-9a64-6bdd4be2ee4',
        'web-client-id-product',
        '2023-07-12 07:33:42',
        '$2a$10$.J0Rfg7y2Mu8AN8Dk2vL.eBFa9NGbOYCPOAFEw.QhgGLVXjO7eFDC',
        NULL,
        'web平台-SSO客户端-商品服务',
        'client_secret_basic',
        'refresh_token,authorization_code',
        'http://127.0.0.1:9002/login/oauth2/code/product-client',
        'http://127.0.0.1:9000/',
        'openid,profile',
#         将settings.client.require-authorization-consent值设置为false后，上面的测试，首次访问商品服务进行登录时，输入用户名、密码提交后，将不会跳转到授权确认页面
        '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}',
        '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",1800.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",3600.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.device-code-time-to-live\":[\"java.time.Duration\",300.000000000]}');

INSERT INTO `oauth-server`.`oauth2_registered_client` (
                                                       `id`,
                                                       `client_id`,
                                                       `client_id_issued_at`,
                                                       `client_secret`,
                                                       `client_secret_expires_at`,
                                                       `client_name`,
                                                       `client_authentication_methods`,
                                                       `authorization_grant_types`,
                                                       `redirect_uris`,
                                                       `post_logout_redirect_uris`,
                                                       `scopes`,
                                                       `client_settings`,
                                                       `token_settings`)
VALUES ('3eacac0e-0de9-4727-9a64-6bdd4be2ee6',
        'web-client-id-gateway',
        '2023-07-12 07:33:42',
        '$2a$10$.J0Rfg7y2Mu8AN8Dk2vL.eBFa9NGbOYCPOAFEw.QhgGLVXjO7eFDC',
        NULL,
        '网关服务',
        'client_secret_basic',
        'refresh_token,authorization_code',
        'http://mall-gateway:9003/login/oauth2/code/gateway-client',
        'http://127.0.0.1:9000/',
        'openid,profile',
        '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.client.require-proof-key\":false,\"settings.client.require-authorization-consent\":true}',
        '{\"@class\":\"java.util.Collections$UnmodifiableMap\",\"settings.token.reuse-refresh-tokens\":true,\"settings.token.id-token-signature-algorithm\":[\"org.springframework.security.oauth2.jose.jws.SignatureAlgorithm\",\"RS256\"],\"settings.token.access-token-time-to-live\":[\"java.time.Duration\",1800.000000000],\"settings.token.access-token-format\":{\"@class\":\"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat\",\"value\":\"self-contained\"},\"settings.token.refresh-token-time-to-live\":[\"java.time.Duration\",3600.000000000],\"settings.token.authorization-code-time-to-live\":[\"java.time.Duration\",300.000000000],\"settings.token.device-code-time-to-live\":[\"java.time.Duration\",300.000000000]}');


INSERT INTO `oauth-server`.`sys_user` (`id`, `username`, `password`, `name`, `description`, `status`)
VALUES (1, 'jay', '$2a$10$8fyY0WbNAr980e6nLcPL5ugmpkLLH3serye5SJ3UcDForTW5b0Sx.', '测试用户', 'Spring Security 测试用户', 1);

package com.emezon.cart.domain.spi.external;

import com.emezon.cart.domain.models.external.User;

public interface IUserExternalOutPort {

    User findByEmail(String email);

}

package com.swiftdroid.posterhouse.service;

import com.swiftdroid.posterhouse.model.UserShipping;

public interface UserShippingService {

	
UserShipping findById(Long id);

void removeById(Long userShippingId);


}
package com.springbootproject.requests;

import java.util.Date;

public record ProductUpdateRequest(String title, String image, String description, Date date ) {
}

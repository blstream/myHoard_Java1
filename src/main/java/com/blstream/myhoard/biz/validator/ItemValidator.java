package com.blstream.myhoard.biz.validator;

import com.blstream.myhoard.biz.enums.RequestMethodEnum;
import com.blstream.myhoard.biz.model.ItemDTO;
import com.blstream.myhoard.biz.service.ItemService;
import com.blstream.myhoard.exception.MyHoardException;
import com.blstream.myhoard.exception.ValidatorException;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemValidator extends AbstractValidator {

    private static final String KEY_NAME = "name";
    private static final String KEY_PATTERN = "pattern";
    private static final String KEY_LOCATION_LAT = "location.lat";
    private static final String KEY_LOCATION_LNG = "location.lng";

    private static final String NAME_LENGTH = "Length of name must be between 2 and 20 characters";
    private static final String NAME_EXIST = "Item name: %s, already exist";
    private static final String LOCATION_LAT = "lat value: %s is invalid. Latitude values must be between -90 and 90 degrees";
    private static final String LOCATION_LNG = "lng value: %s is invalid. Longitude values must be between -180 and 180 degrees";
    
    @Autowired
    private ItemService itemService;
    
    public void validate(ItemDTO itemDTO, RequestMethodEnum requestMethod) throws MyHoardException {
        errorMessages = new HashMap<>();

        switch (requestMethod) {
            case POST:
            case PUT:
                validateItemDTO(itemDTO);
                break;
            default:
                break;
        }

        checkError();
    }

    private void validateItemDTO(ItemDTO itemDTO) throws MyHoardException {
        // name
        if (itemDTO.getName() != null) {
            itemDTO.setName(itemDTO.getName().trim());
        }
        if (itemDTO.getName() == null) {
            errorMessages.put(KEY_NAME, MESSAGE_NOT_EMPTY);
        } else if (itemDTO.getName().length() < 2 || itemDTO.getName().length() > 50) {
            errorMessages.put(KEY_NAME, String.format(MESSAGE_LENGTH_MIN_MAX, 2, 50));
        } else if (!itemService.isUniqueNameOfCollectionItem(itemDTO.getName(), Integer.parseInt(itemDTO.getCollection()))) {
            errorMessages.put(KEY_NAME, String.format(NAME_EXIST, itemDTO.getName()));
        }

        if (itemDTO.getLocation() != null) {
            if (itemDTO.getLocation().getLat() == null || itemDTO.getLocation().getLat() < -90 || itemDTO.getLocation().getLat() > 90) {
                errorMessages.put(KEY_LOCATION_LAT, String.format(LOCATION_LAT, itemDTO.getLocation().getLat()));
            }
            if (itemDTO.getLocation().getLng() == null || itemDTO.getLocation().getLng() < -180 || itemDTO.getLocation().getLng() > 180) {
                errorMessages.put(KEY_LOCATION_LNG, String.format(LOCATION_LNG, itemDTO.getLocation().getLng()));
            }
        }
    }

	public void validatePattern(String name) {
		errorMessages = new HashMap<>();
		if(name != null && ( name.length() < 2 || name.length() > 20)) {
			errorMessages.put(KEY_PATTERN, NAME_LENGTH);
		}
		checkError();
	}
	
    private void checkError() {
        if (!errorMessages.isEmpty()) {
            throw new ValidatorException(errorMessages);
        }
    }
}

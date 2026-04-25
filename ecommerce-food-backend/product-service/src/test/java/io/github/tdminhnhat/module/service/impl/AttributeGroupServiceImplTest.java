package io.github.tdminhnhat.module.service.impl;

import io.github.tdminhnhat.module.entity.AttributeGroup;
import io.github.tdminhnhat.module.model.dto.AttributeGroupDto;
import io.github.tdminhnhat.module.repository.AttributeGroupRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Attribute Group Service - Testing")
class AttributeGroupServiceImplTest {

    // Repository
    @Mock
    AttributeGroupRepository attributeGroupRepository;

    // Service Testing
    @InjectMocks
    AttributeGroupServiceImpl attributeGroupService;

    // Entity
    AttributeGroup attributeGroup;

    @BeforeEach
    void setup() {

    }

    @Nested
    @DisplayName("Attribute Group - Testing Save")
    class AttributeGroupSavingTest {

        @Test
        @DisplayName("Saving AttributeGroup - Successfully")
        void saveAttributeGroupSuccessfully() {
            // Give

            // Handle

            // Check

        }

        @Test
        @DisplayName("Saving AttributeGroup - Duplicate Data - Failed")
        void saveAttributeGroupDuplicateDate() {
            // Give

            // Handle

            // Check

        }
    }

    @Nested
    @DisplayName("Attribute Group - Updating Test")
    class AttributeGroupUpdatingTest {

        @Test
        @DisplayName("Updating AttributeGroup - Successfully")
        void updateAttributeGroupSuccessfully() {

        }

        @Test
        @DisplayName("Updating AttributeGroup - Duplicate Data - Failed")
        void updateAttributeGroupDuplicateData() {

        }

        @Test
        @DisplayName("Updating AttributeGroup - Id Not Exist - Failed")
        void updateAttributeGroupWithIdNotExist() {

        }
    }
}
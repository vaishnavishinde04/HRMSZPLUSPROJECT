package com.HRMS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import com.HRMS.controller.AllowanceController;
import com.HRMS.dao.AllowanceDAO;
import com.HRMS.model.AllowanceMaster;
import com.HRMS.service.AllowanceService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AllowanceControllerTest {

    @Mock
    private AllowanceService allowanceService;

    @InjectMocks
    private AllowanceController allowanceController;
   
    @BeforeEach
    public void setUp() {
    	allowanceService = Mockito.mock(AllowanceService.class);
    	MockitoAnnotations.initMocks(this);
        allowanceController = new AllowanceController();
        allowanceController.setAllowanceService(allowanceService);
    }
    

    @Test
    public void testGetAllAllowances() {
        // Mock data for testing
        List<AllowanceMaster> mockAllowances = new ArrayList<>();
        mockAllowances.add(new AllowanceMaster());

        // Mock the behavior of the service
        when(allowanceService.getAllAllowances()).thenReturn(mockAllowances);

        Model model = Mockito.mock(Model.class);

        String viewName = allowanceController.getAllAllowances(model);

        // Verify that the viewName is as expected
        assertEquals("/Allowance/AllowanceMaintenance", viewName);

        // Add more assertions as needed, e.g., check if the model contains the expected attributes
    }

    @Test
    public void testCreateAllowanceForm() {
        Model model = Mockito.mock(Model.class);

        String viewName = allowanceController.createAllowanceForm(model);

        // Verify that the viewName is as expected
        assertEquals("/Allowance/NewAllowance", viewName);

        // Add more assertions as needed
    }

    @Test
    void testSaveAllowance() {
        // Arrange
        AllowanceMaster newAllowance = new AllowanceMaster();
        newAllowance.setAllowanceName("New Allowance");
        newAllowance.setAllowanceDescription("New Description");

        AllowanceMaster savedAllowance = new AllowanceMaster();
        savedAllowance.setAllowanceId(1);
        savedAllowance.setAllowanceName("New Allowance");
        savedAllowance.setAllowanceDescription("New Description");

        // Mock the behavior of the service
        Mockito.when(allowanceService.saveAllowance(newAllowance)).thenReturn(savedAllowance);

        // Act
        String viewName = allowanceController.addAllowance(newAllowance);

        // Assert
        assertEquals("redirect:/allowances", viewName);
    }

    @Test
    public void testDeleteAllowance() {
        // Arrange
        int allowanceIdToDelete = 1;

        // Mock the behavior of the service
        doNothing().when(allowanceService).deleteAllowance(allowanceIdToDelete);

        // Act
        String viewName = allowanceController.deleteAllowance(allowanceIdToDelete);

        // Assert
        assertEquals("redirect:/allowances", viewName); // Assuming you're redirecting after deletion
    }
    
//    @Test
//    void testUpdateAllowance() {
//        // Arrange
//        AllowanceMaster updatedAllowance = new AllowanceMaster();
//        // Set properties for the updated allowance
//
//        // Mock the behavior of the service
//        Mockito.when(allowanceService.updateAllowance(updatedAllowance)).thenReturn(updatedAllowance);
//
//        // Act
//        AllowanceMaster result = allowanceController.updateallowance(updatedAllowance);
//
//        // Assert
//        assertNotNull(result);
//        // Add assertions to check if the result matches the expected updated allowance.
//    }

}

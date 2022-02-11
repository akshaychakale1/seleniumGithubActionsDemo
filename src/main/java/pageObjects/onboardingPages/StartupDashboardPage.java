package pageObjects.onboardingPages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import reusableComponents.ExplicitWaits;
import reusableComponents.Log;
import testBase.TestBase;

public class StartupDashboardPage extends TestBase {

    @FindBy(xpath = "//button[@data-target='#addSolutionModal']")
    WebElement addSolutionButton;

    @FindBy(xpath = "//ng-select[@id='addUcsIndustry']//input")
    WebElement industryField;

    @FindBy(xpath = "//div[@class='ng-dropdown-panel-items scroll-host']//div[@role='option']")
    WebElement dropdownOption;

    @FindBy(xpath = "//ng-select[@id='addUcsFunction']//input")
    WebElement functionField;

    @FindBy(xpath = "//ng-select[@id='addUcsTechnology']//input")
    WebElement addUcsTechnology;

    @FindBy(xpath = "//ng-select[@id='addUcsTags']//input")
    WebElement addUcsTags;

    @FindBy(xpath = "//button[@id='solutionSubmit']")
    WebElement publishBtn;

    @FindBy(xpath = "//button[@id='skipAndSubmit']")
    WebElement saveAsDraft;


    @FindBy(xpath = "//div[@class='usecase-card-wrapper ng-star-inserted']")
    WebElement solutionTile;

    @FindBy(xpath = "//div[@class='usecase-card-wrapper ng-star-inserted']//img[1]")
    WebElement solutionCollaborators;

    @FindBy(xpath = "//div[@class='status-message']//input")
    WebElement emailField;

    @FindBy(xpath = "//button[normalize-space()='Invite']")
    WebElement inviteButton;

    @FindBy(xpath = "(//img[@aria-label='Close'])[last()]")
    WebElement closeCollabButton;

    @FindBy(xpath = "//div[@class='account_hover position-relative ng-star-inserted']")
    WebElement myAccountSidebar;

    @FindBy(xpath = "//div[contains(@class,'user-item')][1]")
    WebElement myAccountOption;

    @FindBy(xpath = "//div[contains(@class,'user-item')][3]")
    WebElement logout;

    @FindBy(xpath = "//img[@class='usecase-bookmark ng-star-inserted']")
    WebElement bookmarkSolution;

    @FindAll({@FindBy(xpath = "//div[@class='usecase-card-wrapper ng-star-inserted']//img[3]"),
    @FindBy(xpath = "//img[@class='dots']")})
    WebElement solutionOptions;

    @FindBy(xpath = "//div[@class='col-12 options-item'][1]")
    WebElement openInNewTabOption;

    @FindBy(xpath = "//div[@class='col-12 options-item'][2]")
    WebElement manageCollaboratorsOption;

    @FindBy(xpath = "//div[@class='col-12 options-item ng-star-inserted'][1]")
    WebElement moveToDraftOption;

    @FindBy(xpath = "//button[@class='btn btn-primary ml-3 confirm-btn continue-btn ng-star-inserted']")
    WebElement moveToDraftButton;

    @FindBy(xpath = "//div[@class='col-12 options-item ng-star-inserted'][2]")
    WebElement moveToArchiveOption;

    @FindBy(xpath = "//button[@class='btn btn-primary ml-3 confirm-btn continue-btn ng-star-inserted']")
    WebElement archiveButton;

    @FindBy(xpath = "//button[@class='btn btn-primary ml-auto confirm-btn']")
    WebElement cancelButton;

    @FindBy(xpath = "//button[contains(.,'Opportunities')]")
    WebElement solutionOpportunityButton;


    @FindBy(xpath = "//input[@id='inputName']")
    WebElement nameField;

    @FindBy(xpath = "//input[@id='inputName']//following::div[1]")
    WebElement nameFieldError;

    @FindBy(xpath = "//input[@id='inputRole']")
    WebElement roleField;

    @FindBy(xpath = "//input[@id='inputRole']//following::div[1]")
    WebElement roleFieldError;

    @FindBy(xpath = "//input[@id='inputEmail']")
    WebElement myProfileEmailField;

    @FindBy(xpath = "//input[@id='inputLinkedin']")
    WebElement linkedinField;

    @FindBy(xpath = "//input[@id='inputLinkedin']//following::div[1]")
    WebElement linkedinFieldError;

    //change password
    @FindBy(xpath = "//div[@id='changePassword']")
    WebElement myAccountChangePassword;

    @FindBy(xpath = "//input[@id='currentPassword']")
    WebElement currentPassword;

    @FindBy(xpath = "//div[@class='error-class ng-star-inserted']")
    WebElement currentPasswordError;

    @FindBy(xpath = "//input[@id='newPassword']")
    WebElement newPassword;

    @FindBy(xpath = "//input[@id='newPassword']//following::div")
    WebElement newPasswordError;

    @FindBy(xpath = "//input[@id='verifyPassword']")
    WebElement verifyPassword;

    @FindBy(xpath = "//input[@id='verifyPassword']//following::div")
    WebElement verifyPasswordError;

    @FindBy(xpath = "//button[@class='save-changes']")
    WebElement saveChangesButton;

    @FindBy(xpath = "//input[@id='currentPassword']//following-sibling::a")
    WebElement forgotPasswordLink;

    //Company Profile
    @FindBy(xpath = "//div[@id='companyProfile']")
    WebElement myAccountCompanyProfile;

    @FindBy(xpath = "//input[@id='companyName']")
    WebElement companyNameField;

    @FindBy(xpath = "//input[@id='companyName']//following-sibling::div")
    WebElement companyNameFieldError;

    @FindBy(xpath = "//input[@class='ng-untouched ng-pristine ng-valid']")
    WebElement companyLocationField;

    @FindBy(xpath = "//input[@id='inputWebsite']")
    WebElement companyWebsiteField;

    @FindBy(xpath = "//input[@id='inputWebsite']//following-sibling::div")
    WebElement companyWebsiteFieldError;

    @FindBy(xpath = "//input[@id='inputLinkedin']")
    WebElement companyLinkedinField;

    @FindBy(xpath = "//input[@id='inputLinkedin']//following-sibling::div")
    WebElement companyLinkedinFieldError;

    // My Team
    @FindBy(xpath = "//div[@id='myTeam']")
    WebElement myAccountMyTeam;

    @FindBy(css = "[class='button-class ng-star-inserted']")
    WebElement inviteMembersButton;

    @FindBy(xpath = "//input[@id='ucsInviteMang']")
    WebElement solutionInviteEmailField;

    @FindBy(xpath = "//button[@class='button-class']")
    WebElement solutionInviteButton;

    @FindBy(xpath = "//div[@class='pt-3 ng-star-inserted']//label")
    WebElement usecaseCheckBox;

    //use-case Expanded view
    @FindBy(xpath = "(//div[@class='mat-tab-body-wrapper']//img)[1]")
    WebElement useCaseEditIcon;

    @FindBy(xpath = "//button[@class='btn mr-2 mb-1 tag_items ng-star-inserted']")
    WebElement useCaseTag;

    @FindBy(xpath = "//button[@id='ucsDiscardChange']")
    WebElement discardChangesButton;

    @FindBy(xpath = "//button[@id='ucsSaveChanges']")
    WebElement saveChanges;

    //comments
    @FindBy(xpath = "//div[@id='mat-tab-label-0-1']")
    WebElement commentsTab;

    @FindBy(xpath = "//div[@class='add-comment-wrapper']//input")
    WebElement commentInputField;

    @FindBy(xpath = "//div[@class='add-comment-wrapper']//img")
    WebElement sendIcon;

    @FindBy(xpath = "//div[@class='comment-card']")
    WebElement comment;

    //Files
    @FindBy(xpath = "//div[@id='mat-tab-label-0-2']")
    WebElement filesTab;

    @FindBy(xpath = "//input[@id='file']")
    WebElement chooseFile;

    @FindBy(xpath = "(//div[@class='img-with-text files col-4'])[1]")
    WebElement linkIcon;

    @FindBy(xpath = "//table[@id='theTable']//input")
    WebElement linkInputField;

    @FindBy(xpath = "//table[@id='theTable']//following::div[1]")
    WebElement linkFieldError;

    @FindBy(xpath = "//table[@id='theTable']//td[2]")
    WebElement selectIcon;

    @FindBy(xpath = "//table[@id='theTable']//td[3]")
    WebElement cancelIcon;

    @FindBy(xpath = "//div[@class='d-flex invite-user p-2 mt-2']")
    WebElement expandedViewCollaborators;



    @FindBy(css = "[role$='gridcell']")//[class='mat-row cdk-row ng-star-inserted']
    WebElement matchedOpportunity;

    @FindBy(xpath = "(//div[@class=\"status-drop-down\"]//img)[1]")
    WebElement statusDropdown;

    @FindBy(xpath = "(//div[@class=\"status-options-item\"])[1]")
    WebElement newStatusOption;

    @FindBy(xpath = "(//div[@class=\"status-options-item\"])[2]")
    WebElement evaluateStatusOption;

    @FindBy(xpath = "(//div[@class=\"status-options-item\"])[3]")
    WebElement pocStatusOption;

    @FindBy(xpath = "(//div[@class=\"status-options-item\"])[4]")
    WebElement contractStatusOption;

    @FindBy(xpath = "(//div[@class=\"status-options-item\"])[5]")
    WebElement rejectedStatusOption;

    @FindBy(xpath = "(//div[@class=\"status-drop-down\"]//p)[1]")
    WebElement currentStatus;

    @FindBy(xpath = "//button[.=' Edit Labels']")
    WebElement editLabel;

    @FindBy(xpath = "(//button[.=' Apply'])[1]")
    WebElement applyButton;

    //List/kanban view
    @FindBy(xpath = "//div[@role='tab']/following-sibling::div[1]")
    WebElement kanbanViewTab;

    @FindBy(xpath = "(//div[@class='mat-tab-labels']//div)[1]")
    WebElement listViewTab;

    @FindBy(xpath = "//div[@class='inner-cdk-drag']")
    WebElement kanbanViewCard;

    @FindBy(xpath = "//div[@id='new']")
    WebElement kanbanNewContainer;

    @FindBy(xpath = "//div[@id='evaluate']")
    WebElement kanbanEvaluateContainer;

    @FindBy(xpath = "//div[@id='poc']")
    WebElement kanbanPocContainer;

    @FindBy(xpath = "//div[@id='contract']")
    WebElement kanbanContractContainer;

    @FindBy(xpath = "//div[@id='rejected']")
    WebElement kanbanRejectedContainer;

    //Expanded view status changes
    @FindBy(xpath = "(//div[@class=\"is-option status-drop-down\"])[1]")
    WebElement statusDropdownEV;

    @FindBy(xpath = "(//div[@class=\"is-option status-options-item\"])[1]")
    WebElement newStatusOptionEV;

    @FindBy(xpath = "(//div[@class=\"is-option status-options-item\"])[2]")
    WebElement evaluateStatusOptionEV;

    @FindBy(xpath = "(//div[@class=\"is-option status-options-item\"])[3]")
    WebElement pocStatusOptionEV;

    @FindBy(xpath = "(//div[@class=\"is-option status-options-item\"])[4]")
    WebElement contractStatusOptionEV;

    @FindBy(xpath = "(//div[@class=\"is-option status-options-item\"])[5]")
    WebElement rejectedStatusOptionEV;

    @FindBy(xpath = "(//div[@class=\"is-option status-drop-down\"]//p)[1]")
    WebElement currentStatusEV;

    //for use case sharing functionalities
    @FindBy(xpath = "//p[contains(.,'Your account is currently under review.')]")
    WebElement underReviewWarning;

    public StartupDashboardPage(){
        PageFactory.initElements(driver,this);
    }

    public void clickOnMyAccountSidebar(){
        myAccountSidebar.click();
        Log.info("clicked on my account sidebar");
    }

    public void clickOnMyAccountOption(){
        myAccountOption.click();
        Log.info("clicked on my account option");
    }

    public void clickOnLogoutOption(){
        commonMethods.moveToElement(driver,myAccountOption);
        logout.click();
        Log.info("clicked on logout");
    }

    public void setDashboardSolutionIndustryField(String industry) {
        industryField.sendKeys(industry);
        ExplicitWaits.explicitWaitVisibilityOfElement(dropdownOption,5);
        dropdownOption.click();
        Log.info("user added Solution industry");
    }

    public void setDashboardSolutionFunctionField(String functions){
        functionField.sendKeys(functions);
        ExplicitWaits.explicitWaitVisibilityOfElement(dropdownOption,5);
        dropdownOption.click();
        Log.info("user added Solution functions");
    }

    public void setDashboardSolutionTechnologiesField(String technologies) {
        addUcsTechnology.sendKeys(technologies);
        ExplicitWaits.explicitWaitVisibilityOfElement(dropdownOption,5);
        dropdownOption.click();
        Log.info("user added Solution technologies");
    }

    public void setDashboardSolutionTagsField(String tags) {
        addUcsTags.sendKeys(tags);
        ExplicitWaits.explicitWaitVisibilityOfElement(dropdownOption,5);
        dropdownOption.click();
        Log.info("user added Solution tags");
    }

    public void clickOnPublishSolution(){
        ExplicitWaits.explicitWaitVisibilityOfElement(publishBtn,50);
        ExplicitWaits.explicitWaitElementToBeClickable(publishBtn,50);
        try {
            publishBtn.click();
        }catch (ElementClickInterceptedException e) {
            Log.info("ElementClickInterceptedException");
            commonMethods.clickElementByJS(publishBtn,driver);
        }

        Log.info("clicked on publish");
    }

    public void clickOnSolutionTile(){
        solutionTile.click();
        Log.info("clicked on solution tile");
    }

    public void clickOnSolutionCollaborators(){
        ExplicitWaits.explicitWaitVisibilityOfElement(solutionCollaborators,60);
//        solutionCollaborators.click();
        commonMethods.clickElementByJS(solutionCollaborators,driver);
        Log.info("clicked on solution collaborators");
    }

    public void inviteCollaborators(String email) {
        emailField.sendKeys(email);
        Log.info("invite mail entered");
        emailField.sendKeys(Keys.ENTER);
        Log.info("clicked enter");
        ExplicitWaits.explicitWaitElementToBeClickable(inviteButton,30);
        inviteButton.click();
        Log.info("clicked on invite button");
        closeCollabButton.click();
        Log.info("clicked on close button");
    }





    public void bookmarkSolution(){
        bookmarkSolution.click();
        Log.info("solution bookmarked");
    }

    public void clickOnSolutionOptions(){
        ExplicitWaits.explicitWaitVisibilityOfElement(solutionOptions,50);
//        solutionOptions.click();
        commonMethods.clickElementByJS(solutionOptions,driver);
        Log.info("clicked on solution options (three dot button)");
    }

    public void clickOnOpenInNewTabOption(){
        openInNewTabOption.click();
        Log.info("opened usecase in new tab");
    }

    public void clickOnMoveToDraftOption(){
        moveToDraftOption.click();
        Log.info("clicked on move to draft");
    }

    public void confirmMoveToDraftButton(){
        moveToDraftButton.click();
        Log.info("clicked on move to draft");
    }

    public void clickOnMoveToArchiveOption(){
        moveToArchiveOption.click();
        Log.info("clicked on confirm move to archive button");
    }

    public void confirmMoveToArchiveButton(){
        archiveButton.click();
        Log.info("clicked confirm archiveButton");
    }

    public void clickOnOpportunitiesButton(){
        //solutionOpportunityButton.click();
        commonMethods.retryingFindClick(solutionOpportunityButton);
        Log.info("clicked on solution OpportunityButton button");
    }

    public void myProfileFieldTest()  {
        ngDriver.waitForAngularRequestsToFinish();
        nameField.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        nameField.sendKeys(Keys.chord(Keys.BACK_SPACE));
        Log.info("name field cleared");
        roleField.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        roleField.sendKeys(Keys.chord(Keys.BACK_SPACE));
        Log.info("role field cleared");
        myProfileEmailField.click();
        Assert.assertFalse(myProfileEmailField.isEnabled());
        Assert.assertEquals(nameFieldError.getText(),"Please enter your name");
        Assert.assertEquals(roleFieldError.getText(),"Please enter your role");
        nameField.sendKeys(faker.number().digits(6));
        roleField.sendKeys(faker.company().profession());
        Assert.assertEquals(nameFieldError.getText(),"Name should only contains alphabets.");
        nameField.sendKeys(faker.name().fullName());

        //Linkedin field
        linkedinField.clear();
        linkedinField.sendKeys("https://www.fb.com/in/thexfuture");
        myProfileEmailField.click();
        Assert.assertEquals(linkedinFieldError.getText(),"The linkedIn url seems invalid, please try again");
    }

    public void clickOnCompanyProfile(){
        myAccountCompanyProfile.click();
        Log.info("clicked on company profile");
    }

    public void verifyCompanyProfileFields(){
        ngDriver.waitForAngularRequestsToFinish();
        companyNameField.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        companyNameField.sendKeys(Keys.BACK_SPACE);
        companyWebsiteField.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        companyWebsiteField.sendKeys(Keys.BACK_SPACE);
        companyLinkedinField.sendKeys("https://www.fb.com/in/thy5d");
        myAccountCompanyProfile.click();
        Assert.assertEquals(companyNameFieldError.getText(),"Please enter your company’s name");
        Assert.assertEquals(companyWebsiteFieldError.getText(),"Please enter your company's website");
        Assert.assertEquals(companyLinkedinFieldError.getText(),"The linkedIn url seems invalid, please try again");
    }

    public void clickOnMyTeam(){
        myAccountMyTeam.click();
        Log.info("clicked on my team");
    }

    public void verifyInviteMemberFunctionality() throws Exception {
        inviteMembersButton.click();
        Log.info("clicked on invite member");
        String email= faker.name().username()+"@mail7.io";
        solutionInviteEmailField.sendKeys(email);
        solutionInviteEmailField.sendKeys(Keys.ENTER);
        Assert.assertFalse(solutionInviteButton.isEnabled());
        usecaseCheckBox.click();
        ExplicitWaits.explicitWaitElementToBeClickable(solutionInviteButton,30);
        solutionInviteButton.click();

        String invite = commonMethods.fetchEmailLink(email);
        Assert.assertFalse(invite.isEmpty());
    }

    public void clickOnChangePassword(){
        myAccountChangePassword.click();
        Log.info("clicked on change password");
    }

    public void changePasswordFunctionalityTest(String email){
        String current = "Password@123";
        String newPass = "Diatoz@123";

        //verify empty fields error
        saveChangesButton.click();
        Assert.assertEquals(currentPasswordError.getText(),"Current password is required");
        Assert.assertEquals(newPasswordError.getText(),"New password is required");
        Assert.assertEquals(verifyPasswordError.getText(),"Verify Password is required");

        //verify with wrong current password
        currentPassword.sendKeys("WrongPass@123");
        newPassword.sendKeys(newPass);
        verifyPassword.sendKeys(newPass);
        saveChangesButton.click();
        ngDriver.waitForAngularRequestsToFinish();
        //Assert.assertEquals(currentPasswordError.getText(),"wrong current password");

        //verify different password in new password and verify password field
        currentPassword.clear();
        currentPassword.sendKeys(current);
        newPassword.clear();
        newPassword.sendKeys(newPass);
        verifyPassword.clear();
        verifyPassword.sendKeys("Missmatch@123");
        saveChangesButton.click();
        Assert.assertEquals(verifyPasswordError.getText(),"New password and verify password must match");

        //verify with Valid details
        currentPassword.clear();
        currentPassword.sendKeys(current);
        newPassword.clear();
        newPassword.sendKeys(newPass);
        verifyPassword.clear();
        verifyPassword.sendKeys(newPass);
        saveChangesButton.click();
        ngDriver.waitForAngularRequestsToFinish();

        txfLoginPage.enterEmail(email);
        txfLoginPage.enterPassword(newPass);
        txfLoginPage.clickLoginButton();
    }

    public void verifyUseCaseExpandedView(){
        useCaseEditIcon.click();
        Log.info("clicked on use case edit icon");
        useCaseTag.click();
        Log.info("clicked on use case tag");
        commonMethods.scrollToElement(driver,discardChangesButton);
        discardChangesButton.click();
        Log.info("clicked on discardChanges Button");
        commonMethods.scrollToElement(driver,commentsTab);
    }

    public void verifyCommentsFunctionality(){
        commentsTab.click();
        Log.info("clicked on comments tab");
        ExplicitWaits.explicitWaitVisibilityOfElement(commentInputField,30);
        commentInputField.sendKeys(faker.lorem().sentence(2));
        Log.info("entered comment");
        sendIcon.click();
        Log.info("clicked on send comment icon");
        ExplicitWaits.explicitWaitVisibilityOfElement(comment,20);
        Assert.assertTrue(comment.isDisplayed());

        commonMethods.scrollToElement(driver,commentInputField);
        ExplicitWaits.explicitWaitVisibilityOfElement(commentInputField,30);
        commentInputField.sendKeys(faker.lorem().sentence(2));
        Log.info("entered comment");
        commentInputField.sendKeys(Keys.ENTER);
        Log.info("clicked enter");
        ExplicitWaits.explicitWaitVisibilityOfElement(comment,20);
        Assert.assertTrue(comment.isDisplayed());
    }

    public void verifyFilesTabFunctionality(){
        commonMethods.clickElementByJS(filesTab,driver);
        //filesTab.click();
        Log.info("clicked on files tab");
        String file =  System.getProperty("user.dir")+"/src/test/resources/testData/test.docx";
        chooseFile.sendKeys(file);
        Log.info("uploaded file");
        ExplicitWaits.explicitWaitVisibilityOfElement(By.xpath("//div[@class='attachment-row-class row ng-star-inserted']"),40);
        linkIcon.click();
        Log.info("clicked on link icon");
        linkInputField.sendKeys("https://docs.google.com/document/d/188Sr1h8pbzuI");
        Log.info("entered document link");
        selectIcon.click();
        Log.info("clicked on select icon");
        ExplicitWaits.explicitWaitVisibilityOfElement(By.xpath("(//div[@class='attachment-row-class row ng-star-inserted'])[2]"),40);
    }


    public void verifyStatusDropdownFunctionality(){
        driver.navigate().refresh();
//        ngDriver.waitForAngularRequestsToFinish();
        ExplicitWaits.explicitWaitVisibilityOfElement(statusDropdown,30);
        statusDropdown.click();
        Log.info("clicked on status Dropdown");

        evaluateStatusOption.click();
        Log.info("clicked on evaluate StatusOption");
//        ngDriver.waitForAngularRequestsToFinish();
        ExplicitWaits.explicitWaitTextToBePresentInElement(currentStatus,30,"Evaluate");
        Assert.assertEquals(currentStatus.getText(),"Evaluate");

        statusDropdown.click();
        Log.info("clicked on status Dropdown");
        pocStatusOption.click();
        Log.info("clicked on poc Option");
//        ngDriver.waitForAngularRequestsToFinish();
        ExplicitWaits.explicitWaitTextToBePresentInElement(currentStatus,30,"Poc");
        Assert.assertEquals(currentStatus.getText(),"Poc");

        statusDropdown.click();
        Log.info("clicked on status Dropdown");
        contractStatusOption.click();
        Log.info("clicked on  contract Option");
//        ngDriver.waitForAngularRequestsToFinish();
        ExplicitWaits.explicitWaitTextToBePresentInElement(currentStatus,30,"Contract");
        Assert.assertEquals(currentStatus.getText(),"Contract");

        statusDropdown.click();
        Log.info("clicked on status Dropdown");
        rejectedStatusOption.click();
        Log.info("clicked on  rejected Option");
//        ngDriver.waitForAngularRequestsToFinish();
        ExplicitWaits.explicitWaitTextToBePresentInElement(currentStatus,30,"Rejected");
        Assert.assertEquals(currentStatus.getText(),"Rejected");

        statusDropdown.click();
        Log.info("clicked on status Dropdown");
        newStatusOption.click();
        Log.info("clicked on  new Option");
//        ngDriver.waitForAngularRequestsToFinish();
        ExplicitWaits.explicitWaitTextToBePresentInElement(currentStatus,30,"New");
        Assert.assertEquals(currentStatus.getText(),"New");
    }


    public void verifyKanbanViewFunctionality(){
        kanbanViewTab.click();
        Log.info("clicked on kanbanViewTab");
        driver.navigate().refresh();
        ExplicitWaits.waitForPageToLoad();
        ExplicitWaits.explicitWaitElementToBeClickable(kanbanViewCard, 30);

        Actions builder = new Actions(driver);
        builder.clickAndHold(kanbanViewCard)
                .moveToElement(kanbanEvaluateContainer)
                .release(kanbanViewCard)
                .build().perform();
        Log.info("moved card to evaluate");

        ExplicitWaits.explicitWaitVisibilityOfElement(By.xpath("(//div[@id='evaluate']//div)[1]"),20);
        commonMethods.dragAndDrop(driver,kanbanViewCard,kanbanPocContainer);
        Log.info("moved card to poc");

        ExplicitWaits.explicitWaitVisibilityOfElement(By.xpath("(//div[@id='poc']//div)[1]"),20);

    }


    public void clickOnMatchedOpportunity(){
        ExplicitWaits.explicitWaitVisibilityOfElement(matchedOpportunity,50);
        matchedOpportunity.click();
        Log.info("clicked on matched opportunity");
    }

    public void verifyExpandedViewStatusDropdownFunctionality() {
        driver.navigate().refresh();

        ExplicitWaits.explicitWaitVisibilityOfElement(statusDropdownEV,30);
        statusDropdownEV.click();
        Log.info("clicked on status Dropdown of expanded view");

        evaluateStatusOptionEV.click();
        Log.info("clicked on evaluate StatusOption");
        ExplicitWaits.explicitWaitTextToBePresentInElement(currentStatusEV,20,"Evaluate");
        Assert.assertEquals(currentStatusEV.getText(),"Evaluate");

        ExplicitWaits.explicitWaitVisibilityOfElement(statusDropdownEV,30);
        statusDropdownEV.click();
        Log.info("clicked on status Dropdown of expanded view");
        pocStatusOptionEV.click();
        Log.info("clicked on poc Option");
        ExplicitWaits.explicitWaitTextToBePresentInElement(currentStatusEV,20,"Poc");
        Assert.assertEquals(currentStatusEV.getText(),"Poc");

        statusDropdownEV.click();
        Log.info("clicked on status Dropdown");
        contractStatusOptionEV.click();
        Log.info("clicked on  contract Option");
        ExplicitWaits.explicitWaitTextToBePresentInElement(currentStatusEV,20,"Contract");
        Assert.assertEquals(currentStatusEV.getText(),"Contract");

        statusDropdownEV.click();
        Log.info("clicked on status Dropdown");
        rejectedStatusOptionEV.click();
        Log.info("clicked on  rejected Option");
        ExplicitWaits.explicitWaitTextToBePresentInElement(currentStatusEV,20,"Rejected");
        Assert.assertEquals(currentStatusEV.getText(),"Rejected");

        statusDropdownEV.click();
        Log.info("clicked on status Dropdown");
        newStatusOptionEV.click();
        Log.info("clicked on  new Option");
        ExplicitWaits.explicitWaitTextToBePresentInElement(currentStatusEV,20,"New");
        Assert.assertEquals(currentStatusEV.getText(),"New");
    }

    public void verifyAccountUnderReviewWarning(){
        ExplicitWaits.explicitWaitVisibilityOfElement(solutionTile,50);
        Assert.assertTrue(underReviewWarning.isDisplayed(),"before admin approval, account under review warning should be displayed");
    }

    public void verifyAddSolutionIsDisabled(){
        Assert.assertFalse(addSolutionButton.isEnabled(),"add solution button should be disabled before admin admin approval");
    }


}

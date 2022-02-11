package pageObjects.onboardingPages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import reusableComponents.ExplicitWaits;
import reusableComponents.Log;
import testBase.TestBase;

public class EnterpriseDashboardPage extends TestBase {

    @FindBy(xpath = "//div[@class='ng-star-inserted']")
    WebElement myAccountSidebar;

    @FindBy(xpath = "//div[contains(@class,'user-item')][1]")
    WebElement myAccountOption;

    @FindBy(xpath = "//div[contains(@class,'user-item')][2]")
    WebElement support;

    @FindBy(xpath = "//div[contains(@class,'user-item')][3]")
    WebElement logout;

    //Create usecase from dashboard
    @FindBy(xpath = "//button[@class='btn btn-primary addusecase-btn ng-star-inserted']")
    WebElement clickAddUsc;

    @FindBy(css = "p[data-target='#createUsecase']")
    WebElement clickCreateOwnUsc;

    @FindBy(css = "p[data-target='#templateList']")
    WebElement chooseFromTemplates;
    //
    @FindBy(xpath = "//button[@id='ucsSubmitButton']")
    WebElement publishBtn;

    @FindBy(xpath = "//button[@id='saveAsDraft']")
    WebElement saveAsDraft;

    @FindBy(xpath = "//button[@class='btn Choose-from-templates my-auto']")
    WebElement getChooseFromTemplates;

//    @FindBy(css = "p[data-target='#templateList']")
//    WebElement chooseFromTemplates;
//    //
//    @FindBy(xpath = "//button[@id='ucsSubmitButton']")
//    WebElement publishBtn;
//
//    @FindBy(xpath = "//button[@id='saveAsDraft']")
//    WebElement saveAsDraft;

    @FindBy(xpath = "//div[@class='usecase-card-wrapper ng-star-inserted']")
    WebElement usecaseTile;

    @FindBy(xpath = "//img[@class='mat-tooltip-trigger ml-auto collaborator']")
    WebElement shareUseCaseIcon;

    @FindBy(xpath = "(//p[@class='more']//img)[1]")
    WebElement usecaseCollaborators;

    @FindBy(xpath = "//div[@class='status-message']//input")
    WebElement emailField;

    @FindBy(xpath = "//button[normalize-space()='Invite']")
    WebElement inviteButton;

    @FindBy(xpath = "//img[@aria-label='Close']")
    WebElement closeCollabButton;

    @FindBy(xpath = "//img[@class='usecase-bookmark ng-star-inserted']")
    WebElement bookmarkUsecase;

    @FindBy(xpath = "//img[@class='options']")
    WebElement usecaseOptions;

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

    @FindBy(xpath = "//button[@class='usecase-solution ng-star-inserted']")
    WebElement usecaseSolutionsButton;


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
    WebElement usecaseInviteEmailField;

    @FindBy(xpath = "//button[@class='button-class']")
    WebElement usecaseInviteButton;

    @FindBy(xpath = "//div[@class='pt-3 ng-star-inserted']//label")
    WebElement usecaseCheckBox;

    //use-case Expanded view
    @FindBy(xpath = "//img[@class='mat-tooltip-trigger ml-auto ng-star-inserted']")
    WebElement useCaseEditIcon;

    @FindBy(xpath = "//button[@class='btn mr-2 mb-1 tag_items ng-star-inserted']")
    WebElement useCaseTag;

    @FindBy(xpath = "//button[@id='ucsDiscardChange']")
    WebElement discardChangesButton;

    @FindBy(xpath = "//button[@id='ucsSaveChanges']")
    WebElement saveChanges;

    //comments
    @FindBy(xpath = "//a[contains(text(),'Comments')]")
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

    @FindBy(xpath = "(//div[@class='mt-4 ng-star-inserted']//div)[3]")
    WebElement linkIcon;

    @FindBy(xpath = "//table[@id='theTable']//input")
    WebElement linkInputField;

    @FindBy(xpath = "//table[@id='theTable']//following::div[1]")
    WebElement linkFieldError;

    @FindBy(xpath = "//table[@id='theTable']//td[2]")
    WebElement selectIcon;

    @FindBy(xpath = "//table[@id='theTable']//td[3]")
    WebElement cancelIcon;

    @FindAll({@FindBy(xpath = "//div[@class='d-flex invite-user p-2 mt-2']"),
            @FindBy(css = "[class='collaborator']")})
    WebElement expandedViewCollaborators;



    @FindBy(xpath = "//div[@class='d-flex invite-user p-1 mt-2']//following-sibling::button")
    WebElement expandedViewSolutionsButton;

    @FindBy(xpath = "//tbody/mat-row[1]/mat-cell[1]/span[1]")
    WebElement matchedSolution;

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


    @FindBy(xpath = "//div[@class=\"add-column-icon ng-star-inserted\"]//img")
    WebElement addColumn;

    @FindBy(css = "[class=\"column-list-wrapper d-block\"]")
    WebElement addColumnPopup;

    @FindBy(xpath = "//span[contains(text(),'ARR')]")
    WebElement arrColumn;

    @FindBy(xpath = "//span[contains(text(),'Funding Round')]")
    WebElement fRound;

    @FindBy(xpath = "//span[contains(text(),'Funding Raised')]")
    WebElement fRaised;

    @FindBy(xpath = "stars-line")
    WebElement ratings;

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
    @FindBy(xpath = "(//div[@class=\"is-option status-drop-down\"])[1]//img")
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

    @FindBy(xpath = "//div[@class='col-3 sharing-sidebar ng-star-inserted']")
    WebElement useCaseSharingTab;

    @FindBy(xpath = "//div[@class='mat-slide-toggle-thumb']")
    WebElement publicSharingToggleSwitch;

    @FindBy(xpath = "//img[@class='globe-icon ng-star-inserted']")
    WebElement globeIcon;

    @FindBy(xpath = "//div[@id='publicLink']")
    WebElement publicLink;

    @FindBy(xpath = "//h3[@class='public-sharing preview-embed']//span")
    WebElement embedCodePreview;

    @FindBy(xpath = "(//span[@class='preview'])[2]")
    WebElement publicLinkPreview;

    @FindBy(xpath = "//p[@class='align-self-center']")
    WebElement companyTitleInPreview;

    @FindBy(xpath = "//button[.='APPLY NOW']")
    WebElement previewApplyNowButton;



    public EnterpriseDashboardPage() {
        PageFactory.initElements(driver, this);
    }

    public void clickOnMyAccountSidebar(){
        ExplicitWaits.explicitWaitElementToBeClickable(myAccountSidebar,60);
        commonMethods.retryingFindClick(myAccountSidebar);
        //myAccountSidebar.click();
        Log.info("clicked on my account sidebar");
    }

    public void clickOnMyAccountOption(){
        ExplicitWaits.explicitWaitVisibilityOfElement(myAccountOption,50);
        myAccountOption.click();
        Log.info("clicked on my account option");
    }

    public void clickOnLogoutOption(){
        commonMethods.moveToElement(driver,myAccountOption);
        logout.click();
        Log.info("clicked on logout");
    }

    public void setClickAddUsc(){
        ExplicitWaits.explicitWaitVisibilityOfElement(clickAddUsc,50);
        try {
            clickAddUsc.click();
        }catch (ElementClickInterceptedException e){
            commonMethods.clickElementByJS(clickAddUsc,driver);
        }

        Log.info("clicked on Add usecase button");
    }

    public void setClickCreateOwnUsc(){
        clickCreateOwnUsc.click();
        Log.info("clicked on Create Own Usc");
    }

    public void setChooseFromTemplates(){
        chooseFromTemplates.click();
        Log.info("clicked on choose From Templates");
    }

    public void setPublishBtn(){
        publishBtn.click();
        Log.info("clicked on publish Btn");
    }

    public void setSaveAsDraft(){
        try {
            saveAsDraft.click();
        } catch (ElementClickInterceptedException e){
            commonMethods.clickElementByJS(saveAsDraft,driver);
        }
        Log.info("clicked on save As Draft");
    }

    public void setGetChooseFromTemplates(){
        getChooseFromTemplates.click();
        Log.info("clicked on choose from templates");
    }

    public void clickOnUsecaseTile(){
        usecaseTile.click();
        Log.info("clicked on usecase tile");
    }

    public void clickOnShareUseCaseIcon(){
        shareUseCaseIcon.click();
        Log.info("clicked on share use case icon");
    }

    public void clickOnUsecaseCollaborators(){
        ExplicitWaits.explicitWaitVisibilityOfElement(usecaseCollaborators,60);
        //usecaseCollaborators.click();
        commonMethods.clickElementByJS(usecaseCollaborators,driver);
        Log.info("clicked on usecase collaborators");
    }

    public void inviteCollaborators(String email)  {
        emailField.sendKeys(email);
        Log.info("invite mail entered");
        emailField.sendKeys(Keys.ENTER);
        Log.info("clicked enter");
        inviteButton.click();
        Log.info("clicked on invite button");
        closeCollabButton.click();
        Log.info("clicked on close button");
    }

    public void bookmarkUsecase(){
        bookmarkUsecase.click();
        Log.info("usecase bookmarked");
    }

    public void clickOnUsecaseOptions(){
        usecaseOptions.click();
        Log.info("clicked on usecase options (three dot button)");
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

    public void clickOnUsecaseSolutionsButton(){
        usecaseSolutionsButton.click();
        Log.info("clicked on usecase solution button");
    }

    public void myProfileFieldTest(){
        ExplicitWaits.explicitWaitVisibilityOfElement(nameField,50);
        nameField.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        nameField.sendKeys(Keys.chord(Keys.BACK_SPACE));
        Log.info("name field cleared");
        roleField.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        roleField.sendKeys(Keys.chord(Keys.BACK_SPACE));
        Log.info("role field cleared");
        myProfileEmailField.click();
        Assert.assertFalse(myProfileEmailField.isEnabled());
//        Assert.assertEquals(nameFieldError.getText(),"Please enter your name");
//        Assert.assertEquals(roleFieldError.getText(),"Please enter your role");
        nameField.sendKeys("8446949");
        roleField.sendKeys(faker.company().profession());
//        Assert.assertEquals(nameFieldError.getText(),"Name should only contains alphabets.");
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
        ExplicitWaits.explicitWaitVisibilityOfElement(companyNameField,50);
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

    public void verifyInviteMemberFunctionality()  {
        ExplicitWaits.explicitWaitVisibilityOfElement(inviteMembersButton,50);
        inviteMembersButton.click();
        Log.info("clicked on invite member");
        String email= faker.name().username()+"@tafmail.com";
        usecaseInviteEmailField.sendKeys(email);
        usecaseInviteEmailField.sendKeys(Keys.ENTER);
        Assert.assertFalse(usecaseInviteButton.isEnabled());
        ExplicitWaits.waitTillAngularRequestIsFinish();
        usecaseCheckBox.click();
        ExplicitWaits.explicitWaitElementToBeClickable(usecaseInviteButton,30);
        ExplicitWaits.waitTillAngularRequestIsFinish();
        usecaseInviteButton.click();

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
        //Assert.assertEquals(currentPasswordError.getText(),"wrong current password");

        //verify different password in new password and verify password field
        ExplicitWaits.waitTillAngularRequestIsFinish();
        currentPassword.clear();
        currentPassword.sendKeys(current);
        newPassword.clear();
        newPassword.sendKeys(newPass);
        verifyPassword.clear();
        verifyPassword.sendKeys("Missmatch@123");
        saveChangesButton.click();
        ExplicitWaits.waitTillAngularRequestIsFinish();
        ExplicitWaits.explicitWaitVisibilityOfElement(verifyPasswordError,50);
        Assert.assertEquals(verifyPasswordError.getText(),"New password and verify password must match");

        //verify with Valid details
        currentPassword.clear();
        currentPassword.sendKeys(current);
        newPassword.clear();
        newPassword.sendKeys(newPass);
        verifyPassword.clear();
        verifyPassword.sendKeys(newPass);
        saveChangesButton.click();
        ExplicitWaits.waitTillAngularRequestIsFinish();

        txfLoginPage.enterEmail(email);
        txfLoginPage.enterPassword(newPass);
        txfLoginPage.clickLoginButton();
    }

    public void verifyUseCaseExpandedView(){
        //commonMethods.retryingFindClick(useCaseEditIcon);
        useCaseEditIcon.click();
        Log.info("clicked on use case edit icon");
        ExplicitWaits.explicitWaitVisibilityOfElement(useCaseTag,50);
        useCaseTag.click();
        Log.info("clicked on use case tag");
        commonMethods.scrollToElement(driver,discardChangesButton);
        discardChangesButton.click();
        Log.info("clicked on discardChanges Button");
        commonMethods.scrollToElement(driver,commentsTab);
    }

    public void verifyCommentsFunctionality()  {
        commentsTab.click();
        Log.info("clicked on comments tab");
        ExplicitWaits.explicitWaitVisibilityOfElement(commentInputField,30);

        commentInputField.sendKeys(faker.lorem().sentence(2));
        Log.info("entered comment");
        sendIcon.click();
        Log.info("clicked on send comment icon");
        ExplicitWaits.explicitWaitVisibilityOfElement(comment,20);
        Assert.assertTrue(comment.isDisplayed());
        commentInputField.sendKeys(faker.lorem().sentence(2));
        Log.info("entered comment");
        commentInputField.sendKeys(Keys.ENTER);
        Log.info("clicked enter");
        ExplicitWaits.explicitWaitVisibilityOfElement(comment,20);
        Assert.assertTrue(comment.isDisplayed());
    }

    public void verifyFilesTabFunctionality(){
        try {
            filesTab.click();
        }catch (Exception e){
            commonMethods.clickElementByJS(filesTab,driver);
        }
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

    public void verifyExpandedViewCollaboratorsFunctionality() throws Exception {
        driver.navigate().refresh();
        filesTab.click();
        Log.info("clicked on files tab");
        expandedViewCollaborators.click();
        Log.info("clicked on expandedView Collaborators");
        String email= "meggan.breitenberg@tafmail.com";
        ExplicitWaits.explicitWaitVisibilityOfElement(emailField,50);
        emailField.sendKeys(email);
        emailField.sendKeys(Keys.ENTER);
        Log.info("invite mail entered :"+email);
        inviteButton.click();
        Log.info("clicked on invite button");
        String invite = commonMethods.fetchEmailLink(email);
        Assert.assertFalse(invite.isEmpty(),"invitation mail is not received");
    }

    public void clickOnExpandedViewSolutionsButton(){
        commonMethods.retryingFindClick(expandedViewSolutionsButton);
        // expandedViewSolutionsButton.click();
        Log.info("clicked on expanded View Solutions Button");
    }


    public void clickOnMatchedSolution(){
        listViewTab.click();
        Log.info("clicked on list view tab");

        ExplicitWaits.explicitWaitVisibilityOfElement(matchedSolution,50);
        matchedSolution.click();
        Log.info("clicked on matched solution");
    }

    public void verifyAddColumnFunctionality() {
        driver.navigate().refresh();

        ExplicitWaits.explicitWaitVisibilityOfElement(addColumn, 30);
        addColumn.click();
        Log.info("clicked on add column");
        Assert.assertTrue(addColumnPopup.isDisplayed());
//        Assert.assertEquals(addColumnPopup.getText(), "Add Column");

        arrColumn.click();
        Log.info("clicked on arr");

        ExplicitWaits.explicitWaitVisibilityOfElement(addColumn, 30);
        addColumn.click();
        fRound.click();
        Log.info("clicked on funding round");


        ExplicitWaits.explicitWaitVisibilityOfElement(addColumn, 30);
        addColumn.click();
        fRaised.click();
        Log.info("clicked on funding raised");
       // ExplicitWaits.waitTillAngularRequestIsFinish();
    }

    public void verifyStatusDropdownFunctionality(){

        ExplicitWaits.explicitWaitVisibilityOfElement(statusDropdown,50);
        try {
            commonMethods.retryingFindClick(statusDropdown);
        }catch (ElementClickInterceptedException e){
            e.getMessage();
            commonMethods.clickElementByJS(statusDropdown,driver);
        }
//        statusDropdown.click();
        Log.info("clicked on status Dropdown");
        ExplicitWaits.explicitWaitVisibilityOfElement(evaluateStatusOption,30);
        evaluateStatusOption.click();
        Log.info("clicked on evaluate StatusOption");
        ExplicitWaits.explicitWaitTextToBePresentInElement(currentStatus,30,"Evaluate");
        Assert.assertEquals(currentStatus.getText(),"Evaluate");

        statusDropdown.click();
        Log.info("clicked on status Dropdown");
        ExplicitWaits.explicitWaitVisibilityOfElement(pocStatusOption,50);
        pocStatusOption.click();
        Log.info("clicked on poc Option");
        ExplicitWaits.explicitWaitTextToBePresentInElement(currentStatus,30,"Poc");
        Assert.assertEquals(currentStatus.getText(),"Poc");

        statusDropdown.click();
        Log.info("clicked on status Dropdown");
        ExplicitWaits.explicitWaitVisibilityOfElement(contractStatusOption,50);
        contractStatusOption.click();
        Log.info("clicked on  contract Option");
        ExplicitWaits.explicitWaitTextToBePresentInElement(currentStatus,30,"Contract");
        Assert.assertEquals(currentStatus.getText(),"Contract");

        statusDropdown.click();
        Log.info("clicked on status Dropdown");
        ExplicitWaits.explicitWaitVisibilityOfElement(rejectedStatusOption,50);
        rejectedStatusOption.click();
        Log.info("clicked on  rejected Option");
        ExplicitWaits.explicitWaitTextToBePresentInElement(currentStatus,30,"Rejected");
        Assert.assertEquals(currentStatus.getText(),"Rejected");

        statusDropdown.click();
        Log.info("clicked on status Dropdown");
        ExplicitWaits.explicitWaitVisibilityOfElement(newStatusOption,50);
        newStatusOption.click();
        Log.info("clicked on  new Option");
        ExplicitWaits.explicitWaitTextToBePresentInElement(currentStatus,30,"New");
        Assert.assertEquals(currentStatus.getText(),"New");
    }

    public void verifyKanbanViewFunctionality(){

        ExplicitWaits.explicitWaitVisibilityOfElement(statusDropdown,30);
//        statusDropdown.click();
        commonMethods.retryingFindClick(statusDropdown);
        Log.info("clicked on status Dropdown");
        evaluateStatusOption.click();
        Log.info("clicked on evaluate StatusOption");
        ExplicitWaits.waitTillAngularRequestIsFinish();
        Assert.assertEquals(currentStatus.getText(),"Evaluate");

        kanbanViewTab.click();
        Log.info("clicked on kanbanViewTab");
        ExplicitWaits.waitForPageToLoad();

        ExplicitWaits.explicitWaitVisibilityOfElement(By.xpath("(//div[@id='evaluate']//div)[1]"),20);
        Assert.assertTrue(kanbanCurrentStatus("evaluate"));
        Log.info("evaluate status in kanban view verified");

        listViewTab.click();
        Log.info("clicked on list view tab");
        ExplicitWaits.explicitWaitVisibilityOfElement(statusDropdown,30);
//        statusDropdown.click();
        commonMethods.clickElementByJS(statusDropdown,driver);
        Log.info("clicked on status Dropdown");
        ExplicitWaits.explicitWaitVisibilityOfElement(pocStatusOption,50);
        pocStatusOption.click();
        Log.info("clicked on poc Option");
        ExplicitWaits.waitTillAngularRequestIsFinish();
        Assert.assertEquals(currentStatus.getText(),"Poc");

        kanbanViewTab.click();
        Log.info("clicked on kanban view");
        ExplicitWaits.explicitWaitVisibilityOfElement(By.xpath("(//div[@id='poc']//div)[1]"),20);
        Assert.assertTrue(kanbanCurrentStatus("poc"));
        Log.info("poc status in kanban view verified");

        listViewTab.click();
        Log.info("clicked on list view tab");
        ExplicitWaits.explicitWaitVisibilityOfElement(statusDropdown,30);
//        statusDropdown.click();
        commonMethods.clickElementByJS(statusDropdown,driver);
        Log.info("clicked on status Dropdown");
        ExplicitWaits.explicitWaitVisibilityOfElement(contractStatusOption,50);
        contractStatusOption.click();
        Log.info("clicked on  contract Option");
        ExplicitWaits.waitTillAngularRequestIsFinish();
        Assert.assertEquals(currentStatus.getText(),"Contract");

        kanbanViewTab.click();
        Log.info("clicked on kanban view");
        ExplicitWaits.explicitWaitVisibilityOfElement(By.xpath("(//div[@id='contract']//div)[1]"),20);
        Assert.assertTrue(kanbanCurrentStatus("contract"));
        Log.info("contract status in kanban view verified");

        listViewTab.click();
        Log.info("clicked on list view tab");
        ExplicitWaits.explicitWaitVisibilityOfElement(statusDropdown,30);
//        statusDropdown.click();
        commonMethods.clickElementByJS(statusDropdown,driver);
        Log.info("clicked on status Dropdown");
        ExplicitWaits.explicitWaitVisibilityOfElement(rejectedStatusOption,50);
        rejectedStatusOption.click();
        Log.info("clicked on  rejected Option");
        ExplicitWaits.waitTillAngularRequestIsFinish();
        Assert.assertEquals(currentStatus.getText(),"Rejected");

        kanbanViewTab.click();
        Log.info("clicked on kanban view");
        ExplicitWaits.explicitWaitVisibilityOfElement(By.xpath("(//div[@id='rejected']//div)[1]"),20);
        Assert.assertTrue(kanbanCurrentStatus("rejected"));
        Log.info("Rejected status in kanban view verified");


        listViewTab.click();
        Log.info("clicked on list view tab");
        ExplicitWaits.explicitWaitVisibilityOfElement(statusDropdown,30);
//        statusDropdown.click();
        commonMethods.clickElementByJS(statusDropdown,driver);
        Log.info("clicked on status Dropdown");
        ExplicitWaits.explicitWaitVisibilityOfElement(newStatusOption,50);
        newStatusOption.click();
        Log.info("clicked on  new Option");
        ExplicitWaits.waitTillAngularRequestIsFinish();
        Assert.assertEquals(currentStatus.getText(),"New");

        kanbanViewTab.click();
        Log.info("clicked on kanban view");
        ExplicitWaits.explicitWaitVisibilityOfElement(By.xpath("(//div[@id='new']//div)[1]"),20);
        Assert.assertTrue(kanbanCurrentStatus("new"));
        Log.info("New status in kanban view verified");

    }

    private boolean kanbanCurrentStatus(String status){
        WebElement stat=driver.findElement(By.xpath("(//div[@id='"+status+"']//div)[1]"));
        return stat.isDisplayed();
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

    public void verifyUseCaseSharingTabIsVisible(){
        ExplicitWaits.waitForPageToLoad();
        Assert.assertTrue(useCaseSharingTab.isDisplayed(),"use case sharing tab should be visible");
    }

    public void clickOnPublicSharingToggleSwitch(){
        publicSharingToggleSwitch.click();
        Log.info("clicked on publicSharing Toggle Switch");
        ExplicitWaits.explicitWaitVisibilityOfElement(publicLinkPreview,30);
        Assert.assertTrue(globeIcon.isDisplayed(),"globe icon should be displayed after enabling use case sharing");
    }

    //ToDO
    public void verifyEmbedCodePreview(){
        embedCodePreview.click();
        Log.info("clicked on embedCode preview");

    }

    public String getPublicLink(){
        return publicLink.getText();
    }

    public void verifyPublicLink(String companyName){
        publicLinkPreview.click();
        Log.info("clicked on verify public link");
        ExplicitWaits.explicitWaitVisibilityOfElement(companyTitleInPreview,30);
        Assert.assertEquals(companyTitleInPreview.getText(),companyName,"company name in public link should be same");
    }

    public void clickOnApplyNowButton(){
        commonMethods.scrollToElement(driver,previewApplyNowButton);
        Log.info("scrolled to apply now button");
        ExplicitWaits.explicitWaitElementToBeClickable(previewApplyNowButton,30);
        commonMethods.clickElementByJS(previewApplyNowButton,driver);
        Log.info("clicked on apply now button");
    }
}

/*
 * Copyright 2025 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.specs

import org.openqa.selenium.By
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.verbs.ShouldVerb
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}
import uk.gov.hmrc.selenium.webdriver.{Browser, ScreenshotOnFailure}
import uk.gov.hmrc.ui.pages.*
import uk.gov.hmrc.ui.pages.Vendor.*
import uk.gov.hmrc.ui.pages.Purchaser.*
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation
import uk.gov.hmrc.ui.tags.*

class PurchaserQuestionsSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend Purchaser Questions") {
    Scenario(
      "Complete the Purchaser Questions journey as an Individual and changed to a Company with no purchaser stub data",
      PurchaserJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("no-purchaser"))
      When("the user clicks on the 'Purchaser Questions' link")
      WhoIsTheVendorPage.clickLinkById("task-list-link-purchaser-questions")
      Then("the user is navigated to the Before You Start page")
      PurchaserBeforeYouStartPage.verifyPageTitle(PurchaserBeforeYouStartPage.pageTitle)
      When("the user clicks the Continue button")
      PurchaserBeforeYouStartPage.saveAndContinue()
      Then("the user is navigated to the Who Is Making The Purchase page")
      PurchaserWhoIsMakingThePurchasePage.verifyPageTitle(PurchaserWhoIsMakingThePurchasePage.pageTitle)
      When("the user selects the 'An Individual' radio button")
      PurchaserWhoIsMakingThePurchasePage.radioButton(PurchaserWhoIsMakingThePurchasePage.individual)
      And("clicks the Save and continue button")
      PurchaserWhoIsMakingThePurchasePage.saveAndContinue()
      Then("the user is navigated to the What is the purchaser’s full name? page")
      PurchaserNamePage.verifyPageTitle(PurchaserNamePage.pageTitle)
      When("the user inputs their first name, middle name, and surname")
      PurchaserNamePage.input(
        By.id(PurchaserNamePage.forenameId),
        PurchaserNamePage.forenameInput
      )
      PurchaserNamePage.input(
        By.id(PurchaserNamePage.middlenameId),
        PurchaserNamePage.middlenameInput
      )
      PurchaserNamePage.input(
        By.id(PurchaserNamePage.surnameId),
        PurchaserNamePage.surnameInput
      )
      And("clicks the Save and continue button")
      PurchaserNamePage.saveAndContinue()
      Then("the user is navigated to the Address lookup page")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAddressPage.clickAddressManually()
      And("enters their address manually")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.editPageTitle)
      PurchaserAddressPage.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user is navigated to the Property Address page to 'Review and confirm the address'")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.confirmPageTitle)
      When("the user clicks the Confirm address button")
      PurchaserAddressPage.clickContinueButton()
      Then("the user is navigated to the Do you want to add a phone number? page")
      AddPurchaserPhoneNumber.verifyPageTitle(AddPurchaserPhoneNumber.pageTitle)
      When("the user selects the 'No' radio button")
      AddPurchaserPhoneNumber.radioButton(AddPurchaserPhoneNumber.no)
      And("clicks the Save and continue button")
      AddPurchaserPhoneNumber.saveAndContinue()
      Then("the user is navigated to the Does the Purchaser have a National Insurance number? page")
      AddPurchaserNationalInsuranceNumber.verifyPageTitle(AddPurchaserNationalInsuranceNumber.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddPurchaserNationalInsuranceNumber.radioButton(AddPurchaserNationalInsuranceNumber.yes)
      And("clicks the Save and continue button")
      AddPurchaserNationalInsuranceNumber.saveAndContinue()
      Then("the user is navigated to the What is the Purchaser's national insurance number? page")
      EnterPurchaserNationalInsuranceNumberPage.verifyPageTitle(EnterPurchaserNationalInsuranceNumberPage.pageTitle)
      When("the user inputs a National Insurance Number")
      EnterPurchaserNationalInsuranceNumberPage.input(
        By.id(EnterPurchaserNationalInsuranceNumberPage.Nino),
        EnterPurchaserNationalInsuranceNumberPage.NinoValue
      )
      And("clicks the Save and continue button")
      EnterPurchaserNationalInsuranceNumberPage.saveAndContinue()
      Then("the user is navigated to the What is the purchaser Date of Birth? page")
      DateOfBirthPage.verifyPageTitle(DateOfBirthPage.pageTitle)
      When("the user inputs their Date of Birth")
      DateOfBirthPage.enterDateOfBirth()
      And("clicks the Save and continue button")
      DateOfBirthPage.saveAndContinue()
      Then("the user is navigated to is the Purchaser Acting as a Trustee? page")
      ActingAsATrusteePage.verifyPageTitle(ActingAsATrusteePage.pageTitle)
      When("the user selects the 'Yes' radio button")
      ActingAsATrusteePage.radioButton(ActingAsATrusteePage.yes)
      And("clicks the Save and continue button")
      ActingAsATrusteePage.saveAndContinue()
      Then("the user is navigated to the Are the purchaser and vendor connected? page")
      PurchaserAndVendorConnectedPage.verifyPageTitle(PurchaserAndVendorConnectedPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAndVendorConnectedPage.radioButton(PurchaserAndVendorConnectedPage.yes)
      And("clicks the Save and continue button")
      PurchaserAndVendorConnectedPage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the 'Change' link for purchaser name")
      PurchaserCheckYourAnswersPage.clickPurchaserNameChange()
      Then("the user is navigated to the What is the purchaser’s full name? page")
      PurchaserNamePage.verifyPageTitle(PurchaserNamePage.pageTitle)
      When("the user updates their name")
      PurchaserNamePage.input(By.id(PurchaserNamePage.surnameId), PurchaserNamePage.surnameInput2)
      And("clicks the Save and continue button")
      PurchaserNamePage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the 'Change' link for National Insurance number")
      PurchaserCheckYourAnswersPage.clickPurchaserNINumberChange()
      Then("the user is navigated to the What is Purchaser's national insurance number? page")
      EnterPurchaserNationalInsuranceNumberPage.verifyPageTitle(EnterPurchaserNationalInsuranceNumberPage.pageTitle)
      When("the user updates their National Insurance Number")
      EnterPurchaserNationalInsuranceNumberPage.input(
        By.id(EnterPurchaserNationalInsuranceNumberPage.Nino),
        EnterPurchaserNationalInsuranceNumberPage.NinoValue2
      )
      And("clicks the Save and continue button")
      EnterPurchaserNationalInsuranceNumberPage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the 'Change' link for date of birth")
      PurchaserCheckYourAnswersPage.clickPurchaserDOBChange()
      Then("the user is navigated to the What is the purchaser Date of Birth? page")
      DateOfBirthPage.verifyPageTitle(DateOfBirthPage.pageTitle)
      When("the user updates their Date of Birth")
      DateOfBirthPage.enterDateOfBirth()
      And("clicks the Save and continue button")
      DateOfBirthPage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)

      When("the user clicks on the 'Change' link for purchaser type")
      PurchaserCheckYourAnswersPage.clickPurchaserTypeChange()
      Then("the user is navigated to the Who is making the purchase page")
      PurchaserWhoIsMakingThePurchasePage.verifyPageTitle(PurchaserWhoIsMakingThePurchasePage.pageTitle)
      When("the user selects the 'A Company' radio button")
      PurchaserWhoIsMakingThePurchasePage.radioButton(PurchaserWhoIsMakingThePurchasePage.company)
      And("clicks the Save and continue button")
      PurchaserWhoIsMakingThePurchasePage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the link for entering company name")
      PurchaserCheckYourAnswersPage.clickPurchaserNameChange()
      Then("the user is navigated to the What is the company’s name? page")
      PurchaserNamePage.verifyPageTitle(PurchaserNamePage.pageTitle)
      When("the user inputs their company name")
      PurchaserNamePage.input(
        By.id(PurchaserNamePage.companyId),
        PurchaserNamePage.companyName2
      )
      And("clicks the Save and continue button")
      PurchaserNamePage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the link for entering Form of ID")
      PurchaserCheckYourAnswersPage.clickFormOfIDChange()
      Then("the user is navigated to the Confirm Purchaser Identity page")
      ConfirmPurchaserIdentityPage.verifyPageTitle(ConfirmPurchaserIdentityPage.pageTitle)
      When("the user selects the 'VAT registration number' radio button")
      ConfirmPurchaserIdentityPage.radioButton(ConfirmPurchaserIdentityPage.vatRegistrationNumber)
      And("clicks the Save and continue button")
      ConfirmPurchaserIdentityPage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the link for entering VAT registration number")
      PurchaserCheckYourAnswersPage.clickVATRegistrationNumberChange()
      Then("the user is navigated to the What is the purchaser's VAT Registration number page")
      VATRegistrationNumberPage.verifyPageTitle(VATRegistrationNumberPage.pageTitle)
      When("the user enters their VAT registration number")
      VATRegistrationNumberPage.input(
        By.id(VATRegistrationNumberPage.vat),
        VATRegistrationNumberPage.VATNumber
      )
      And("clicks the Save and continue button")
      VATRegistrationNumberPage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the link for Type of company")
      PurchaserCheckYourAnswersPage.clickTypeOfCompanyChange()
      Then("the user is navigated to the What type of company? page")
      TypeOfCompanyPage.verifyPageTitle(TypeOfCompanyPage.pageTitle)
      When("the user selects 4 types of company")
      TypeOfCompanyPage.checkbox(TypeOfCompanyPage.Bank, true)
      TypeOfCompanyPage.checkbox(TypeOfCompanyPage.Building_Association, true)
      TypeOfCompanyPage.checkbox(TypeOfCompanyPage.Insurance_Assurance_company, true)
      TypeOfCompanyPage.checkbox(TypeOfCompanyPage.Property_company, true)
      And("clicks the Save and continue button")
      TypeOfCompanyPage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)

      When("the user clicks the Save and continue button")
      PurchaserCheckYourAnswersPage.saveAndContinue()
      Then("the user is navigated to the Purchaser Overview page")
      PurchaserOverviewPage.verifyPageTitle(PurchaserOverviewPage.pageTitle)
    }

    Scenario(
      "Complete the Purchaser Questions journey as an Individual with no National Insurance Number and no purchaser stub data",
      PurchaserJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("no-purchaser"))
      When("the user clicks on the 'Purchaser Questions' link")
      WhoIsTheVendorPage.clickLinkById("task-list-link-purchaser-questions")
      Then("the user is navigated to the Before you start page")
      PurchaserBeforeYouStartPage.verifyPageTitle(PurchaserBeforeYouStartPage.pageTitle)
      When("the user clicks the Continue button")
      PurchaserBeforeYouStartPage.saveAndContinue()
      Then("the user is navigated to the Who is making the purchase page")
      PurchaserWhoIsMakingThePurchasePage.verifyPageTitle(PurchaserWhoIsMakingThePurchasePage.pageTitle)
      When("the user selects the 'An Individual' radio button")
      PurchaserWhoIsMakingThePurchasePage.radioButton(PurchaserWhoIsMakingThePurchasePage.individual)
      And("clicks the Save and continue button")
      PurchaserWhoIsMakingThePurchasePage.saveAndContinue()
      Then("the user is navigated to the What is the purchaser’s full name? page")
      PurchaserNamePage.verifyPageTitle(PurchaserNamePage.pageTitle)
      When("the user inputs their first name, middle name, and surname")
      PurchaserNamePage.input(
        By.id(PurchaserNamePage.forenameId),
        PurchaserNamePage.forenameInput
      )
      PurchaserNamePage.input(
        By.id(PurchaserNamePage.middlenameId),
        PurchaserNamePage.middlenameInput
      )
      PurchaserNamePage.input(
        By.id(PurchaserNamePage.surnameId),
        PurchaserNamePage.surnameInput
      )
      And("clicks the Save and continue button")
      PurchaserNamePage.saveAndContinue()
      Then("the user is navigated to the Address lookup page")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAddressPage.clickAddressManually()
      And("enters their address manually")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.editPageTitle)
      PurchaserAddressPage.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user is navigated to the Property Address page to 'Review and confirm the address'")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.confirmPageTitle)
      When("the user clicks the Confirm address button")
      PurchaserAddressPage.clickContinueButton()
      Then("the user is navigated to the Do you want to add phone number? page")
      AddPurchaserPhoneNumber.verifyPageTitle(AddPurchaserPhoneNumber.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddPurchaserPhoneNumber.radioButton(AddPurchaserPhoneNumber.yes)
      And("clicks the Save and continue button")
      AddPurchaserPhoneNumber.saveAndContinue()
      Then("the user is navigated to the What is purchaser's phone number page")
      EnterPurchaserPhoneNumberPage.verifyPageTitle(EnterPurchaserPhoneNumberPage.pageTitle)
      When("the user enters their phone number")
      EnterPurchaserPhoneNumberPage.input(
        By.id(EnterPurchaserPhoneNumberPage.phoneNumberInputField),
        EnterPurchaserPhoneNumberPage.phoneNumberValue
      )
      And("clicks the Save and continue button")
      EnterPurchaserPhoneNumberPage.saveAndContinue()
      Then("the user is navigated to the Does the Purchaser have a National Insurance number? page")
      AddPurchaserNationalInsuranceNumber.verifyPageTitle(AddPurchaserNationalInsuranceNumber.pageTitle)
      When("the user selects the 'No' radio button")
      AddPurchaserNationalInsuranceNumber.radioButton(AddPurchaserNationalInsuranceNumber.no)
      And("clicks the Save and continue button")
      AddPurchaserNationalInsuranceNumber.saveAndContinue()
      Then("the user is navigated to the Provide a form of ID for purchaser page")
      FormOfIDIndividualPage.verifyPageTitle(FormOfIDIndividualPage.pageTitle)
      When("the user inputs their ID number or reference")
      FormOfIDIndividualPage.input(
        By.id(FormOfIDIndividualPage.purchaserIdNumberOrReference),
        FormOfIDIndividualPage.idNumberOrReferenceInput
      )
      And("their Country of issue")
      FormOfIDIndividualPage.input(
        By.id(FormOfIDIndividualPage.purchaserCountryIssued),
        FormOfIDIndividualPage.countryIssuedInput
      )
      And("clicks the Save and Continue button")
      FormOfIDIndividualPage.saveAndContinue()
      Then("the user is navigated to is the Purchaser Acting as a Trustee page")
      ActingAsATrusteePage.verifyPageTitle(ActingAsATrusteePage.pageTitle)
      When("the user selects the 'No' radio button")
      ActingAsATrusteePage.radioButton(ActingAsATrusteePage.no)
      And("clicks the Save and continue button")
      ActingAsATrusteePage.saveAndContinue()
      Then("the user is navigated to the Are the Purchaser and Vendor Connected page")
      PurchaserAndVendorConnectedPage.verifyPageTitle(PurchaserAndVendorConnectedPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAndVendorConnectedPage.radioButton(PurchaserAndVendorConnectedPage.yes)
      And("clicks the Save and continue button")
      PurchaserAndVendorConnectedPage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the 'Change' link for purchaser address")
      PurchaserCheckYourAnswersPage.clickPurchaserAddressChange()
      Then("the user is navigated to the Address lookup page")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAddressPage.clickAddressManually()
      And("enters their updated address manually")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.editPageTitle)
      PurchaserAddressPage.enterAddressManually("123", "TEST", "ZZ11 1ZZ")
      Then("the user is navigated to the Property Address page to 'Review and confirm the address'")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.confirmPageTitle)
      When("the user clicks the Confirm address button")
      PurchaserAddressPage.clickContinueButton()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the 'Change' link for purchasers phone number")
      PurchaserCheckYourAnswersPage.clickPurchaserPhoneNumberChange()
      Then("the user is navigated to the What is purchaser's phone number page")
      EnterPurchaserPhoneNumberPage.verifyPageTitle(EnterPurchaserPhoneNumberPage.pageTitle)
      When("the user updates their phone number")
      EnterPurchaserPhoneNumberPage.input(
        By.id(EnterPurchaserPhoneNumberPage.phoneNumberInputField),
        EnterPurchaserPhoneNumberPage.phoneNumberValue2
      )
      And("clicks the Save and continue button")
      EnterPurchaserPhoneNumberPage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the 'Change' link for Purchaser form of id individual")
      PurchaserCheckYourAnswersPage.clickIndivFormOfIDChange()
      Then("the user is navigated to the Provide a Form of ID for purchaser page")
      FormOfIDIndividualPage.verifyPageTitle(FormOfIDIndividualPage.pageTitle)
      When("the user updates their purchaser ID details")
      FormOfIDIndividualPage.input(
        By.id(FormOfIDIndividualPage.purchaserIdNumberOrReference),
        FormOfIDIndividualPage.idNumberOrReferenceInput2
      )
      And("clicks the Save and continue button")
      FormOfIDIndividualPage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks the Save and continue button")
      PurchaserCheckYourAnswersPage.saveAndContinue()
      Then("the user is navigated to the Purchaser Overview page")
      PurchaserOverviewPage.verifyPageTitle(PurchaserOverviewPage.pageTitle)
    }

    Scenario(
      "Complete the Full Purchaser Questions journey as an Individual with full purchaser with address line 1 stub data",
      PurchaserJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("full-purchaser"))
      When("the user clicks on the 'Purchaser Questions' link")
      WhoIsTheVendorPage.clickLinkById("task-list-link-purchaser-questions")
      Then("the user is navigated to the Purchaser Overview page")
      PurchaserOverviewPage.verifyPageTitle(PurchaserOverviewPage.pageTitle)
      When("the user clicks the 'Remove' link to remove a purchaser")
      PurchaserOverviewPage.clickRemovePurchaser()
      Then("the user is navigated to Are you sure you want to remove the purchaser page")
      PurchaserRemovePage.verifyPageTitle(PurchaserRemovePage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserRemovePage.radioButton(PurchaserRemovePage.yes)
      And("clicks the Save and continue button")
      PurchaserOverviewPage.saveAndContinue()
      Then("the user is navigated to the Purchaser Overview Page")
      PurchaserOverviewPage.verifyPageTitle(PurchaserOverviewPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserOverviewPage.radioButton(PurchaserOverviewPage.yes)
      And("clicks the Continue button")
      PurchaserOverviewPage.saveAndContinue()
      Then("the user is navigated to the Purchaser Before you start page")
      PurchaserBeforeYouStartPage.verifyPageTitle(PurchaserBeforeYouStartPage.pageTitle)
      When("the user clicks the Continue button")
      PurchaserBeforeYouStartPage.saveAndContinue()
      Then("the user is navigated to the Who is making the purchase page")
      PurchaserWhoIsMakingThePurchasePage.verifyPageTitle(PurchaserWhoIsMakingThePurchasePage.pageTitle)
      When("the user selects the 'An Individual' radio button")
      PurchaserWhoIsMakingThePurchasePage.radioButton(PurchaserWhoIsMakingThePurchasePage.individual)
      And("clicks the Save and continue button")
      PurchaserWhoIsMakingThePurchasePage.saveAndContinue()
      Then("the user is navigated to the What is the purchaser’s full name? page")
      PurchaserNamePage.verifyPageTitle(PurchaserNamePage.pageTitle)
      When("the user inputs their first name, middle name, and surname")
      PurchaserNamePage.input(
        By.id(PurchaserNamePage.forenameId),
        PurchaserNamePage.forenameInput
      )
      PurchaserNamePage.input(
        By.id(PurchaserNamePage.middlenameId),
        PurchaserNamePage.middlenameInput
      )
      PurchaserNamePage.input(
        By.id(PurchaserNamePage.surnameId),
        PurchaserNamePage.surnameInput
      )
      And("clicks the Save and continue button")
      PurchaserNamePage.saveAndContinue()
      Then("the user is navigated to the Address lookup page")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAddressPage.clickAddressManually()
      And("enters their address manually")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.editPageTitle)
      PurchaserAddressPage.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user should be navigated to the Property Address page to 'Review and confirm the address'")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.confirmPageTitle)
      When("the user clicks the Confirm address button")
      PurchaserAddressPage.clickContinueButton()
      Then("the user is navigated to is the Purchaser Acting as a Trustee? page")
      ActingAsATrusteePage.verifyPageTitle(ActingAsATrusteePage.pageTitle)
      When("the user selects the 'Yes' radio button")
      ActingAsATrusteePage.radioButton(ActingAsATrusteePage.yes)
      And("clicks the Save and continue button")
      ActingAsATrusteePage.saveAndContinue()
      Then("the user is navigated to the Are the purchaser and vendor connected? page")
      PurchaserAndVendorConnectedPage.verifyPageTitle(PurchaserAndVendorConnectedPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAndVendorConnectedPage.radioButton(PurchaserAndVendorConnectedPage.yes)
      And("clicks the Save and continue button")
      PurchaserAndVendorConnectedPage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      And("clicks the Save and continue button")
      PurchaserCheckYourAnswersPage.saveAndContinue()
      Then("the user is navigated to the Purchaser Overview page")
      PurchaserOverviewPage.verifyPageTitle(PurchaserOverviewPage.pageTitle)
      When("the user clicks the 'Change' link for Purchaser")
      PurchaserOverviewPage.clickPurchaserChange()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
    }

    Scenario(
      "Complete the Purchaser Questions journey as a Company and changing all forms of id with no purchaser stub data",
      PurchaserJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("no-purchaser"))
      When("the user clicks on the 'Purchaser Questions' link")
      WhoIsTheVendorPage.clickLinkById("task-list-link-purchaser-questions")
      Then("the user is navigated to the Before you start page")
      PurchaserBeforeYouStartPage.verifyPageTitle(PurchaserBeforeYouStartPage.pageTitle)
      When("the user clicks the Continue button")
      PurchaserBeforeYouStartPage.saveAndContinue()
      Then("the user is navigated to the Who is making the purchase page")
      PurchaserWhoIsMakingThePurchasePage.verifyPageTitle(PurchaserWhoIsMakingThePurchasePage.pageTitle)
      When("the user selects the 'A Company' radio button")
      PurchaserWhoIsMakingThePurchasePage.radioButton(PurchaserWhoIsMakingThePurchasePage.company)
      And("clicks the Save and continue button")
      PurchaserWhoIsMakingThePurchasePage.saveAndContinue()
      Then("the user is navigated to the What is the company's name? page")
      PurchaserNamePage.verifyPageTitle(PurchaserNamePage.pageTitle)
      When("the user inputs their company name")
      PurchaserNamePage.input(
        By.id(PurchaserNamePage.companyId),
        PurchaserNamePage.companyName2
      )
      And("clicks the Save and continue button")
      PurchaserNamePage.saveAndContinue()
      Then("the user is navigated to the Address lookup page")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.pageTitleCompany)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAddressPage.clickAddressManually()
      And("enters their address manually")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.editPageTitleCompany)
      PurchaserAddressPage.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user is navigated to the Property Address page to 'Review and confirm the address'")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.confirmPageTitleCompany)
      When("the user clicks the Confirm address button")
      PurchaserAddressPage.clickContinueButton()
      Then("the user is navigated to the Do you want to add a phone number? page")
      AddPurchaserPhoneNumber.verifyPageTitle(AddPurchaserPhoneNumber.pageTitle)
      When("the user selects the 'No' radio button")
      AddPurchaserPhoneNumber.radioButton(AddPurchaserPhoneNumber.no)
      And("clicks the Save and continue button")
      AddPurchaserPhoneNumber.saveAndContinue()
      Then("the user is navigated to the Confirm Purchaser Identity page")
      ConfirmPurchaserIdentityPage.verifyPageTitle(ConfirmPurchaserIdentityPage.pageTitle)
      When("the user selects the 'VAT registration number' radio button")
      ConfirmPurchaserIdentityPage.radioButton(ConfirmPurchaserIdentityPage.vatRegistrationNumber)
      And("clicks the Save and continue button")
      ConfirmPurchaserIdentityPage.saveAndContinue()
      Then("the user is navigated to the What is the purchaser's VAT Registration number page")
      VATRegistrationNumberPage.verifyPageTitle(VATRegistrationNumberPage.pageTitle)
      When("the user enters their VAT registration number")
      VATRegistrationNumberPage.input(
        By.id(VATRegistrationNumberPage.vat),
        VATRegistrationNumberPage.VATNumber
      )
      And("clicks the Save and continue button")
      VATRegistrationNumberPage.saveAndContinue()
      Then("the user is navigated to the What type of company? page")
      TypeOfCompanyPage.verifyPageTitle(TypeOfCompanyPage.pageTitle)
      When("the user selects 4 types of company")
      TypeOfCompanyPage.checkbox(TypeOfCompanyPage.Bank, true)
      TypeOfCompanyPage.checkbox(TypeOfCompanyPage.Building_Association, true)
      TypeOfCompanyPage.checkbox(TypeOfCompanyPage.Insurance_Assurance_company, true)
      TypeOfCompanyPage.checkbox(TypeOfCompanyPage.Property_company, true)
      And("clicks the Save and continue button")
      TypeOfCompanyPage.saveAndContinue()
      Then("the user is navigated to is the Purchaser Acting as a Trustee? page")
      ActingAsATrusteePage.verifyPageTitle(ActingAsATrusteePage.pageTitle)
      When("the user selects the 'Yes' radio button")
      ActingAsATrusteePage.radioButton(ActingAsATrusteePage.yes)
      And("clicks the Save and continue button")
      ActingAsATrusteePage.saveAndContinue()
      Then("the user is navigated to the Are the purchaser and vendor connected? page")
      PurchaserAndVendorConnectedPage.verifyPageTitle(PurchaserAndVendorConnectedPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAndVendorConnectedPage.radioButton(PurchaserAndVendorConnectedPage.yes)
      And("clicks the Save and continue button")
      PurchaserAndVendorConnectedPage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the 'Change' link for entering VAT registration number")
      PurchaserCheckYourAnswersPage.clickVATRegistrationNumberChange()
      Then("the user is navigated to the What is the purchaser's VAT Registration number page")
      VATRegistrationNumberPage.verifyPageTitle(VATRegistrationNumberPage.pageTitle)
      When("the user updates their VAT registration number")
      VATRegistrationNumberPage.input(
        By.id(VATRegistrationNumberPage.vat),
        VATRegistrationNumberPage.VATNumber2
      )
      And("clicks the Save and continue button")
      VATRegistrationNumberPage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the 'Change' link for Form of ID")
      PurchaserCheckYourAnswersPage.clickFormOfIDChange()
      Then("the user is navigated to the Confirm Purchaser Identity page")
      ConfirmPurchaserIdentityPage.verifyPageTitle(ConfirmPurchaserIdentityPage.pageTitle)
      When("the user updates to 'Partnership Unique Taxpayer Reference (UTR)' radio button")
      ConfirmPurchaserIdentityPage.radioButton(ConfirmPurchaserIdentityPage.partnershipUTR)
      And("clicks the Save and continue button")
      ConfirmPurchaserIdentityPage.saveAndContinue()
      Then("the user is navigated to the enter Purchaser's Partnership UTR page")
      PartnershipUTRPage.verifyPageTitle(PartnershipUTRPage.pageTitle)
      When("the user inputs their company's Partnership Unique Taxpayer Reference (UTR)")
      PartnershipUTRPage.input(
        By.id(PartnershipUTRPage.purchaserUTRReference),
        PartnershipUTRPage.purchaserUTRInput
      )
      And("clicks the Save and continue button")
      PartnershipUTRPage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the 'Change' link for Form of ID")
      PurchaserCheckYourAnswersPage.clickFormOfIDChange()
      Then("the user is navigated to the Confirm Purchaser Identity page")
      ConfirmPurchaserIdentityPage.verifyPageTitle(ConfirmPurchaserIdentityPage.pageTitle)
      When("the user selects the 'Corporation tax Unique Taxpayer Reference (UTR)' radio button")
      ConfirmPurchaserIdentityPage.radioButton(ConfirmPurchaserIdentityPage.corporationTaxUTR)
      And("clicks the Save and continue button")
      ConfirmPurchaserIdentityPage.saveAndContinue()
      Then("the user is navigated to the enter Purchaser's Corporation Tax UTR page")
      CorporationTaxUTRPage.verifyPageTitle(CorporationTaxUTRPage.pageTitle)
      When("the user inputs their company's Corporation tax Unique Taxpayer Reference (UTR)")
      CorporationTaxUTRPage.input(
        By.id(CorporationTaxUTRPage.corporationTaxUTR),
        CorporationTaxUTRPage.corporationTaxUTRInput
      )
      And("clicks the Save and continue button")
      CorporationTaxUTRPage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the 'Change' link for Form of ID")
      PurchaserCheckYourAnswersPage.clickFormOfIDChange()
      Then("the user is navigated to the Confirm Purchaser Identity page")
      ConfirmPurchaserIdentityPage.verifyPageTitle(ConfirmPurchaserIdentityPage.pageTitle)
      When("the user selects the 'Another form of ID' radio button")
      ConfirmPurchaserIdentityPage.radioButton(ConfirmPurchaserIdentityPage.anotherFormOfID)
      And("clicks the Save and continue button")
      ConfirmPurchaserIdentityPage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the 'Change' link for Form of ID")
      PurchaserCheckYourAnswersPage.clickIdAndCountryIssuedChange()
      Then("the user is navigated to the Provide a Form of ID Company page")
      FormOfIDCompanyPage.verifyPageTitle(FormOfIDCompanyPage.pageTitle)
      When("the user inputs their ID number or reference")
      FormOfIDCompanyPage.input(
        By.id(FormOfIDCompanyPage.idNumberOrReference),
        FormOfIDCompanyPage.idNumberOrReferenceInput
      )
      And("their Country of issue")
      FormOfIDCompanyPage.input(
        By.id(FormOfIDCompanyPage.countryOfIssue),
        FormOfIDCompanyPage.countryOfIssueInput
      )
      And("clicks the Save and continue button")
      FormOfIDCompanyPage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the 'Change' link for Do you want to add phone number")
      PurchaserCheckYourAnswersPage.clickAddPhoneNumberChange()
      Then("the user is navigated to the Do you want to add phone number? page")
      AddPurchaserPhoneNumber.verifyPageTitle(AddPurchaserPhoneNumber.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddPurchaserPhoneNumber.radioButton(AddPurchaserPhoneNumber.yes)
      And("clicks the Save and continue button")
      AddPurchaserPhoneNumber.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the link for entering a phone number")
      PurchaserCheckYourAnswersPage.clickPurchaserPhoneNumberChange()
      Then("the user is navigated to the What is purchaser's phone number page")
      EnterPurchaserPhoneNumberPage.verifyPageTitle(EnterPurchaserPhoneNumberPage.pageTitle)
      When("the user enters their phone number")
      EnterPurchaserPhoneNumberPage.input(
        By.id(EnterPurchaserPhoneNumberPage.phoneNumberInputField),
        EnterPurchaserPhoneNumberPage.phoneNumberValue
      )
      And("clicks the Save and continue button")
      EnterPurchaserPhoneNumberPage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the 'Change' link for Type of company")
      PurchaserCheckYourAnswersPage.clickTypeOfCompanyChange()
      Then("the user is navigated to the What type of company? page")
      TypeOfCompanyPage.verifyPageTitle(TypeOfCompanyPage.pageTitle)
      When("the user updates their types of company")
      TypeOfCompanyPage.checkbox(
        TypeOfCompanyPage.Unincorporated_sole_trader_other_than_builder,
        false
      )
      TypeOfCompanyPage.checkbox(TypeOfCompanyPage.Superannuation_or_pension_fund, false)
      And("clicks the Save and continue button")
      TypeOfCompanyPage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the 'Change' link for Acting as a Trustee")
      PurchaserCheckYourAnswersPage.clickIsCompanyActingAsTrusteeChange()
      Then("the user is navigated to is the Purchaser Acting as a Trustee? page")
      ActingAsATrusteePage.verifyPageTitle(ActingAsATrusteePage.pageTitle)
      When("the user selects the 'No' radio button")
      ActingAsATrusteePage.radioButton(ActingAsATrusteePage.no)
      And("clicks the Save and continue button")
      ActingAsATrusteePage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the 'Change' link for Are the purchaser and vendor connected")
      PurchaserCheckYourAnswersPage.clickAreCompanyAndVendorConnectedChange()
      Then("User should be navigated to Are purchaser and vendor connected page")
      PurchaserAndVendorConnectedPage.verifyPageTitle(PurchaserAndVendorConnectedPage.pageTitle)
      When("the user selects the 'No' radio button")
      PurchaserAndVendorConnectedPage.radioButton(PurchaserAndVendorConnectedPage.no)
      And("clicks the Save and continue button")
      PurchaserAndVendorConnectedPage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks the Save and continue button")
      PurchaserCheckYourAnswersPage.saveAndContinue()
      Then("the user is navigated to the Purchaser Overview page")
      PurchaserOverviewPage.verifyPageTitle(PurchaserOverviewPage.pageTitle)
      When("the user clicks the Add a purchaser button")
      PurchaserOverviewPage.saveAndContinue()
      Then("the user is navigated to the Before you start page")
      PurchaserBeforeYouStartPage.verifyPageTitle(PurchaserBeforeYouStartPage.pageTitle)
    }

    Scenario(
      "Complete the Purchaser Questions journey as a Company and changed to an Individual with incomplete purchaser stub data",
      PurchaserJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("incomplete-purchaser"))
      When("the user clicks on the 'Purchaser Questions' link")
      WhoIsTheVendorPage.clickLinkById("task-list-link-purchaser-questions")
      Then("the user is navigated to the Before you start page")
      PurchaserBeforeYouStartPage.verifyPageTitle(PurchaserBeforeYouStartPage.pageTitle)
      When("the user clicks the Continue button")
      PurchaserBeforeYouStartPage.saveAndContinue()
      Then("the user is navigated to the Confirm purchaser name page")
      ConfirmPurchaserNamePage.verifyPageTitle(ConfirmPurchaserNamePage.pageTitle)
      When("the user selects the 'Yes' radio button")
      ConfirmPurchaserNamePage.radioButton(ConfirmPurchaserNamePage.yes)
      And("clicks the Continue button")
      ConfirmPurchaserNamePage.saveAndContinue()
      Then("the user is navigated to the Address lookup page")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.pageTitleCompanyStub)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAddressPage.clickAddressManually()
      And("enters their address manually")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.editPageTitleCompanyStub)
      PurchaserAddressPage.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user is navigated to the Property Address page to 'Review and confirm the address'")
      PurchaserAddressPage.verifyPageTitle(PurchaserAddressPage.confirmPageTitleCompanyStub)
      When("the user clicks the Confirm address button")
      PurchaserAddressPage.clickContinueButton()
      Then("the user is navigated to the Do you want to add phone number? page")
      AddPurchaserPhoneNumber.verifyPageTitle(AddPurchaserPhoneNumber.pageTitle)
      When("the user selects the 'No' radio button")
      AddPurchaserPhoneNumber.radioButton(AddPurchaserPhoneNumber.no)
      And("clicks the Save and continue button")
      AddPurchaserPhoneNumber.saveAndContinue()
      Then("the user is navigated to the Confirm Purchaser Identity page")
      ConfirmPurchaserIdentityPage.verifyPageTitle(ConfirmPurchaserIdentityPage.pageTitleStub)
      When("the user selects the 'VAT registration number' radio button")
      ConfirmPurchaserIdentityPage.radioButton(ConfirmPurchaserIdentityPage.vatRegistrationNumber)
      And("clicks the Save and continue button")
      ConfirmPurchaserIdentityPage.saveAndContinue()
      Then("the user is navigated to the What is the purchaser's VAT Registration number page")
      VATRegistrationNumberPage.verifyPageTitle(VATRegistrationNumberPage.pageTitle)
      When("the user enters their VAT registration number")
      VATRegistrationNumberPage.input(
        By.id(VATRegistrationNumberPage.vat),
        VATRegistrationNumberPage.VATNumber
      )
      And("clicks the Save and continue button")
      VATRegistrationNumberPage.saveAndContinue()
      Then("the user is navigated to the What type of company? page")
      TypeOfCompanyPage.verifyPageTitle(TypeOfCompanyPage.pageTitle)
      When("the user selects 2 types of company")
      TypeOfCompanyPage.checkbox(TypeOfCompanyPage.Bank, true)
      TypeOfCompanyPage.checkbox(TypeOfCompanyPage.Building_Association, true)
      And("clicks the Save and continue button")
      TypeOfCompanyPage.saveAndContinue()
      Then("the user is navigated to is the Purchaser Acting as a Trustee? page")
      ActingAsATrusteePage.verifyPageTitle(ActingAsATrusteePage.pageTitle)
      When("the user selects the 'Yes' radio button")
      ActingAsATrusteePage.radioButton(ActingAsATrusteePage.yes)
      And("clicks the Save and continue button")
      ActingAsATrusteePage.saveAndContinue()
      Then("the user is navigated to the Are the purchaser and vendor connected? page")
      PurchaserAndVendorConnectedPage.verifyPageTitle(PurchaserAndVendorConnectedPage.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAndVendorConnectedPage.radioButton(PurchaserAndVendorConnectedPage.yes)
      And("clicks the Save and continue button")
      PurchaserAndVendorConnectedPage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the 'Change' link for purchaser type")
      PurchaserCheckYourAnswersPage.clickPurchaserTypeChange()
      Then("the user is navigated to the Who is making the purchase page")
      PurchaserWhoIsMakingThePurchasePage.verifyPageTitle(PurchaserWhoIsMakingThePurchasePage.pageTitle)
      When("the user selects the 'An Individual' radio button")
      PurchaserWhoIsMakingThePurchasePage.radioButton(PurchaserWhoIsMakingThePurchasePage.individual)
      And("clicks the Save and continue button")
      PurchaserWhoIsMakingThePurchasePage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the link for entering the purchaser's full name")
      PurchaserCheckYourAnswersPage.clickPurchaserNameChange()
      Then("the user is navigated to the What is the purchaser’s full name? page")
      PurchaserNamePage.verifyPageTitle(PurchaserNamePage.pageTitle)
      When("the user inputs their first name, middle name, and surname")
      PurchaserNamePage.input(
        By.id(PurchaserNamePage.forenameId),
        PurchaserNamePage.forenameInput
      )
      PurchaserNamePage.input(
        By.id(PurchaserNamePage.middlenameId),
        PurchaserNamePage.middlenameInput
      )
      PurchaserNamePage.input(
        By.id(PurchaserNamePage.surnameId),
        PurchaserNamePage.surnameInput
      )
      PurchaserNamePage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the 'Change' link for Does the Purchaser have a National Insurance number?")
      PurchaserCheckYourAnswersPage.clickDoPurchaserHaveNINumberChange()
      Then("the user is navigated to the Does the Purchaser have a National Insurance number? page")
      AddPurchaserNationalInsuranceNumber.verifyPageTitle(AddPurchaserNationalInsuranceNumber.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddPurchaserNationalInsuranceNumber.radioButton(AddPurchaserNationalInsuranceNumber.yes)
      And("clicks the Save and continue button")
      AddPurchaserNationalInsuranceNumber.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the link for entering National Insurance number")
      PurchaserCheckYourAnswersPage.clickPurchaserNINumberChange()
      Then("the user is navigated to the What is the Purchaser's national insurance number? page")
      EnterPurchaserNationalInsuranceNumberPage.verifyPageTitle(EnterPurchaserNationalInsuranceNumberPage.pageTitle)
      When("the user inputs a National Insurance Number")
      EnterPurchaserNationalInsuranceNumberPage.input(
        By.id(EnterPurchaserNationalInsuranceNumberPage.Nino),
        EnterPurchaserNationalInsuranceNumberPage.NinoValue2
      )
      And("clicks the Save and continue button")
      EnterPurchaserNationalInsuranceNumberPage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      When("the user clicks on the link for entering Date of Birth")
      PurchaserCheckYourAnswersPage.clickPurchaserDOBChange()
      Then("User should be navigated to purchaser date of birth page")
      DateOfBirthPage.verifyPageTitle(DateOfBirthPage.pageTitle)
      When("the user inputs their Date of Birth")
      DateOfBirthPage.enterDateOfBirth()
      And("clicks the Save and continue button")
      DateOfBirthPage.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswersPage.verifyPageTitle(PurchaserCheckYourAnswersPage.pageTitle)
      And("clicks the Save and continue button")
      PurchaserCheckYourAnswersPage.saveAndContinue()
    }
  }
}

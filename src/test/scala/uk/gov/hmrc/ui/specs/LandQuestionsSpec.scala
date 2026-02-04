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

import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.verbs.ShouldVerb
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}
import uk.gov.hmrc.selenium.webdriver.{Browser, ScreenshotOnFailure}
import uk.gov.hmrc.ui.pages.*
import uk.gov.hmrc.ui.pages.Land.*
import uk.gov.hmrc.ui.tags.*
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation

class LandQuestionsSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend About The Land Questions") {
    Scenario(
      "Complete the Land Questions user journey Address confirmed, registered with Land Registry, UPRN available (Title and UPRN captured)",
      LandJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("firstJson"))
      When("the user clicks on the 'Land Questions' link")
      LandBeforeYouStartPage.clickLinkById("task-list-link-land-questions")
      Then("the user should be navigated to the Purchaser Before you start page page")
      LandBeforeYouStartPage.verifyPageTitle(LandBeforeYouStartPage.pageTitle)
      And("clicks the Continue button")
      LandBeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to What is the type of property")
      TypeOfPropertyPage.verifyPageTitle(TypeOfPropertyPage.pageTitle)
      And("the user selects the 'Residential' radio button")
      TypeOfPropertyPage.radioButton(TypeOfPropertyPage.residential)
      And("The user selects save and continue")
      TypeOfPropertyPage.saveAndContinue()
      Then("the user is be navigated to Interest transferred or created page")
      InterestTransferredCreatedPage.verifyPageTitle(InterestTransferredCreatedPage.pageTitle)
      And("the user selects the 'Freehold' radio button and continues")
      InterestTransferredCreatedPage.radioButton(InterestTransferredCreatedPage.FG)
      Then(" the user clicks the Save and continue button")
      InterestTransferredCreatedPage.saveAndContinue()
      // Then("the user is on Confirm the address of the land or property")
      // when the user selects "Yes" and continues
      // when the user is on Title Number for land or property
      // When the user enters a valid title number and continues
      // Then the user is on Do you have an NLPG UPRN for the land or property?
      // When the user selects "Yes" and continues
      // Then the user is on What is the NLPG UPRN for the land or property*/
      // When the user enters a valid NLPG UPRN and continues
      // Then the user is on Will you be sending a plan by post ?
      // When the user  selects yes and continues
      // Then the user is on Minerals or mineral rights(Are there any mineral or mineral rights reserved or excluded for the land or property ?)
      // When the user selects "No" and continues
      // Then the user is on Check your answers
      // When the user selects Save and continue
      // Then the user is on the Overview page
    }
    Scenario(
      "Complete the Land Questions user journey address lookup used, unregistered with Land Registry, no UPRN (Title and UPRN skipped)",
      LandJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("firstJson"))
      When("the user clicks on the 'Land Questions' link")
      LandBeforeYouStartPage.clickLinkById("task-list-link-land-questions")
      Then("the user should be navigated to the Purchaser Before you start page page")
      LandBeforeYouStartPage.verifyPageTitle(LandBeforeYouStartPage.pageTitle)
      And("clicks the Continue button")
      LandBeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to What is the type of property")
      TypeOfPropertyPage.verifyPageTitle(TypeOfPropertyPage.pageTitle)
      And("the user selects the 'Additional Residental Property' radio button")
      TypeOfPropertyPage.radioButton(TypeOfPropertyPage.additional_residential)
      And("The user selects save and continue")
      TypeOfPropertyPage.saveAndContinue()
      Then("the user is be navigated to Interest transferred or created page")
      InterestTransferredCreatedPage.verifyPageTitle(InterestTransferredCreatedPage.pageTitle)
      And("the user selects the 'Long Leasehold' radio button and continues")
      InterestTransferredCreatedPage.radioButton(InterestTransferredCreatedPage.LG)
      Then(" the user clicks the Save and continue button")
      InterestTransferredCreatedPage.saveAndContinue()
      // When the user selects the radio button and continues
      // Then the user is on Confirm the address of the land or property
      // When the user selects "No" and continues
      // Then the user is on Address lookup
      // When the user selects an address and continues
      // Then the user is on What is the local authority code
      // When the user enters a valid local authority code and continues
      // Then the user is on Is the land or property registered with HM Land Registry ?
      // When the user selects "No" and continues
      // Then the user is on Do you have an NLPG UPRN for the land or property ?
      // When the user selects "No" and continues
      // Then the user is on Will you be sending a plan by post ?
      // When the user selects Yes/No radio button and continues
      // Then the user is on Minerals or mineral rights(Are there any mineral or mineral rights reserved or excluded for the land or property ?)
      // When the user selects "No" and continues
      // Then the user is on Check your answers
      // When the user selects Save and continue
      // Then the user is on the Overview page
    }
    Scenario(
      "Complete the Land Questions user journey with Mixed property with mineral rights routes to agricultural land question and exits without land details",
      LandJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("firstJson"))
      When("the user clicks on the 'Land Questions' link")
      LandBeforeYouStartPage.clickLinkById("task-list-link-land-questions")
      Then("the user should be navigated to the Purchaser Before you start page page")
      LandBeforeYouStartPage.verifyPageTitle(LandBeforeYouStartPage.pageTitle)
      And("clicks the Continue button")
      LandBeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to What is the type of property")
      TypeOfPropertyPage.verifyPageTitle(TypeOfPropertyPage.pageTitle)
      And("the user selects the 'Mixed' radio button")
      TypeOfPropertyPage.radioButton(TypeOfPropertyPage.mixed)
      And("The user selects save and continue")
      TypeOfPropertyPage.saveAndContinue()
      Then("the user is be navigated to Interest transferred or created page")
      InterestTransferredCreatedPage.verifyPageTitle(InterestTransferredCreatedPage.pageTitle)
      And("the user selects the 'Leasehold subject' radio button and continues")
      InterestTransferredCreatedPage.radioButton(InterestTransferredCreatedPage.LT)
      Then(" the user clicks the Save and continue button")
      InterestTransferredCreatedPage.saveAndContinue()
      // When the user selects radio button and  continues
      // Then the user is on Confirm the address of the land or property
      // When the user selects "Yes" and continues
      // Then the user is on What is the local authority code
      // When the user enters a valid local authority code and continues
      // Then the user is on Is the land or property registered  with HM Land Registry ?
      // When the user selects "Yes" and continues
      // Then the user is on Title Number for land or property
      // When the user enters a valid title number and continues
      // Then the user is on Do you have an NLPG UPRN for the land or property ?
      // When the user selects "No" and continues
      // Then the user is on Will you be sending a plan by post ?
      // When user selects yes/No radio button and continues
      // Then the user is on Minerals or mineral rights(Are there any mineral or mineral rights reserved or excluded for the land or property ?)
      // When the user selects "Yes" and continues
      // Then the user is on Agricultural or development land (Does the transaction involve agricultural or development land ?)
      // When the user selects "No" and continues
      // Then the user is on Check your answers
      // When the user selects Save and continue
      // Then the user is on the Overview page
    }
    Scenario(
      "Complete the Land Questions user journey for  Non residential property with mineral rights and agricultural land without land area details",
      LandJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("firstJson"))
      When("the user clicks on the 'Land Questions' link")
      LandBeforeYouStartPage.clickLinkById("task-list-link-land-questions")
      Then("the user should be navigated to the Purchaser Before you start page page")
      LandBeforeYouStartPage.verifyPageTitle(LandBeforeYouStartPage.pageTitle)
      And("clicks the Continue button")
      LandBeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to What is the type of property")
      TypeOfPropertyPage.verifyPageTitle(TypeOfPropertyPage.pageTitle)
      And("the user selects the 'Non-Residential' radio button")
      TypeOfPropertyPage.radioButton(TypeOfPropertyPage.non_residential)
      And("The user selects save and continue")
      TypeOfPropertyPage.saveAndContinue()
      Then("the user is be navigated to Interest transferred or created page")
      InterestTransferredCreatedPage.verifyPageTitle(InterestTransferredCreatedPage.pageTitle)
      And("the user selects the 'Other' radio button and continues")
      InterestTransferredCreatedPage.radioButton(InterestTransferredCreatedPage.OT)
      Then(" the user clicks the Save and continue button")
      InterestTransferredCreatedPage.saveAndContinue()
      // And("confirms the address")
      // And("enters the local authority code")
      // And("selects 'Yes' for registered with HM Land Registry")
      // And("enters the title number")
      // And("selects 'No' for having NLPG UPRN")
      // And("selects whether sending a plan by post")
      // And("selects 'Yes' for minerals or mineral rights")
      // And("selects 'Yes' for agricultural or development land")
      // And("selects 'No' for knowing the area of land")
      // Then("the user navigated to CYA page")
    }
    Scenario(
      "Complete the Land Questions user journey for  Non residential property with minerals, agricultural area in square meters",
      LandJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("firstJson"))
      When("the user clicks on the 'Land Questions' link")
      LandBeforeYouStartPage.clickLinkById("task-list-link-land-questions")
      Then("the user should be navigated to the Purchaser Before you start page page")
      LandBeforeYouStartPage.verifyPageTitle(LandBeforeYouStartPage.pageTitle)
      And("clicks the Continue button")
      LandBeforeYouStartPage.saveAndContinue()
      Then("the user should be navigated to What is the type of property")
      TypeOfPropertyPage.verifyPageTitle(TypeOfPropertyPage.pageTitle)
      And("the user selects the 'Non-Residential' radio button")
      TypeOfPropertyPage.radioButton(TypeOfPropertyPage.non_residential)
      And("The user selects save and continue")
      TypeOfPropertyPage.saveAndContinue()
      Then("the user is be navigated to Interest transferred or created page")
      InterestTransferredCreatedPage.verifyPageTitle(InterestTransferredCreatedPage.pageTitle)
      And("the user selects the 'Freehold vacant position' radio button and continues")
      InterestTransferredCreatedPage.radioButton(InterestTransferredCreatedPage.FP)
      Then(" the user clicks the Save and continue button")
      InterestTransferredCreatedPage.saveAndContinue()
      // And("confirms the address")
      // And("enters the local authority code")
      // And("selects 'Yes' for registered with HM Land Registry")
      // And("enters the title number")
      // And("selects 'No' for having NLPG UPRN")
      // And("selects whether sending a plan by post")
      // And("selects 'Yes' for minerals or mineral rights")
      // And("selects 'Yes' for agricultural or development land")
      // And("selects 'Yes' for knowing the area of land")
      // And user navigates to Select one unit of measurement for the area of land
      // And("selects 'square metres' as unit of measurement")
      // And("enters area in square metres")
      // Then("the user navigated to CYA page")
      // And user clicks on change link for unit of measurement
      // And user changes unit of measurement to hectares
      // And user enters area in hectares
      // Then("the user navigated to CYA page")
    }
  }
}

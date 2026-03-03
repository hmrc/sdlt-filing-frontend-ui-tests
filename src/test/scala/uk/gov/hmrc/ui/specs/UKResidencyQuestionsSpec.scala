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
import uk.gov.hmrc.ui.pages.UKResidency.*
import uk.gov.hmrc.ui.tags.*
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation

class UKResidencyQuestionsSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend About The UK Residency Questions") {
    Scenario(
      "Complete the UK Residency Questions for an Individual Purchaser having non-uk residents",
      UKResidencyJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        returnId = Some("individual-purchaser-with-agents-full-land-residential")
      )
      When("the user clicks on the 'UK Residency Questions' link")
      UKResidencyBeforeYouStartPage.clickLinkById("task-list-link-uk-residency-questions")
      Then("the user should be navigated to the UK Residency Before you start page page")
      UKResidencyBeforeYouStartPage.verifyPageTitle(UKResidencyBeforeYouStartPage.pageTitle)
      And("the user click on Continue button")
      UKResidencyBeforeYouStartPage.saveAndContinue()
//      Then ("the user is navigated to Residency Status page")
//      When ("the user selects Yes radio button")
//      And ("clicks on Save & Continue button")
//      Then("the user is navigated to the Crown Employment Relief page")
//      When("the user select Yes radio button")
//      And ("clicks on Save & Continue button")
//      Then ("the user is navigated to CYA page")

    }

    Scenario(
      "Complete the UK Residency Questions for an Individual Purchaser having only uk residents",
      UKResidencyJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        returnId = Some("individual-purchaser-with-agents-full-land-residential")
      )
      When("the user clicks on the 'UK Residency Questions' link")
      UKResidencyBeforeYouStartPage.clickLinkById("task-list-link-uk-residency-questions")
      Then("the user should be navigated to the UK Residency Before you start page page")
      UKResidencyBeforeYouStartPage.verifyPageTitle(UKResidencyBeforeYouStartPage.pageTitle)
      And("the user click on Continue button")
      UKResidencyBeforeYouStartPage.saveAndContinue()
      //      Then ("the user is navigated to Residency Status page")
      //      When ("the user selects No radio button")
      //      And ("clicks on Save & Continue button")
      //      Then ("the user is navigated to CYA page")

    }

    // Stub data for scenario should be changed later to a company type purchaser
    Scenario(
      "Complete the UK Residency Questions where Purchaser is a Company having non-uk residents and non-uk close companies",
      UKResidencyJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        returnId = Some("individual-purchaser-with-agents-full-land-residential")
      )
      When("the user clicks on the 'UK Residency Questions' link")
      UKResidencyBeforeYouStartPage.clickLinkById("task-list-link-uk-residency-questions")
      Then("the user should be navigated to the UK Residency Before you start page page")
      UKResidencyBeforeYouStartPage.verifyPageTitle(UKResidencyBeforeYouStartPage.pageTitle)
      And("the user click on Continue button")
      UKResidencyBeforeYouStartPage.saveAndContinue()
      //      Then ("the user is navigated to Residency Status page")
      //      When ("the user selects Yes radio button")
      //      And ("clicks on Save & Continue button")
      //      Then ("the user is navigated to Close Companies page")
      //      When ("the user selects Yes radio button")
      //      And ("clicks on Save & Continue button")
      //      Then("the user is navigated to the Crown Employment Relief page")
      //      When("the user select Yes radio button")
      //      And ("clicks on Save & Continue button")
      //      Then ("the user is navigated to CYA page")

    }

    // Stub data for scenario should be changed later to a company type purchaser
    Scenario(
      "Complete the UK Residency Questions where Purchaser is a Company having uk residents and no non-uk residents in close companies",
      UKResidencyJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        returnId = Some("individual-purchaser-with-agents-full-land-residential")
      )
      When("the user clicks on the 'UK Residency Questions' link")
      UKResidencyBeforeYouStartPage.clickLinkById("task-list-link-uk-residency-questions")
      Then("the user should be navigated to the UK Residency Before you start page page")
      UKResidencyBeforeYouStartPage.verifyPageTitle(UKResidencyBeforeYouStartPage.pageTitle)
      And("the user click on Continue button")
      UKResidencyBeforeYouStartPage.saveAndContinue()
      //      Then ("the user is navigated to Residency Status page")
      //      When ("the user selects No radio button")
      //      And ("clicks on Save & Continue button")
      //      Then ("the user is navigated to Close Companies page")
      //      When ("the user selects No radio button")
      //      And ("clicks on Save & Continue button")
      //      Then ("the user is navigated to CYA page")

    }
  }
}

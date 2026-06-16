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
import uk.gov.hmrc.ui.pages.DeclarationAndSubmission.{DeclarationConfirmation, WhoAreYouSubmittingThisReturnFor}
import uk.gov.hmrc.ui.tags.*
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation

class DeclarationAndSubmissionSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend Declaration and Submission") {

    Scenario(
      "Complete the Declaration and Submission flow",
      DeclarationAndSubmissionJourney
    ) {

      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        returnId = Some("123456")
      )

      /*     When the user clicks on Submit your Return on the tasklist page
//     Then the user is navigated to the Your Return is ready for Submission page.

       When the user click on View and Print this return
       Then the user is navigated to the completed return form

       When the user click on Continue
       Then the user is navigated to email confirmation page

       When the user selects 'Yes' on email confirmation page
       Then the user is navigated to What email address should HMRC use

       When the user enters email address and clicks Save and Continue
       */
      Then("the user us navigated to Who are you submitting this return for")
      WhoAreYouSubmittingThisReturnFor.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/submit-your-return/who-are-you-submitting-this-return-for"
      )
      WhoAreYouSubmittingThisReturnFor.verifyPageTitle(WhoAreYouSubmittingThisReturnFor.pageTitle)

      When("the user selects purchaserAuthorised and continues")
      WhoAreYouSubmittingThisReturnFor.radioButton(WhoAreYouSubmittingThisReturnFor.purchaserAuthorised)
      WhoAreYouSubmittingThisReturnFor.saveAndContinue()

      Then("the user is navigated to Confirm Declaration ")
      DeclarationConfirmation.verifyPageTitle(DeclarationConfirmation.pageTitle)

      When("the user clicks Confirm and Submit")
      DeclarationConfirmation.saveAndContinue()

    }
  }
}

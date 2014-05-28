/*
 * File created on May 23, 2014 
 *
 * Copyright 2013-2014 Nerdwin15, LLC
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
 *
 */
package com.nerdwin15.wildfly.rewriter;

/**
 * Defines a service that is able to resolve a request URI into a new, 
 * rewritten URI.
 *
 * @author Michael Irwin
 */
public interface RouteResolver {

  /**
   * Resolve the provided requestURI to its rewritten value.
   * <p>
   * The incoming requestURI WILL contain the original servlet context path.
   * For example: /ws/network/users/johnnyLingo
   * <p>
   * The returning (rewritten) URI MUST contain the new servlet context path
   * For example: /userApp/api/users/johnnyLingo
   * 
   * @param requestURI The requestURI to evaluate
   * @return The new resolved route, if determinable. Otherwise, false.
   */
  String resolveRoute(String requestURI);
  
}

package net.vvakame.zaim4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import net.vvakame.util.jsonpullparser.JsonFormatException;

/**
 * API for Zaim.
 * @author vvakame
 */
public class Zaim {

	OAuthConnector connector;


	private Zaim() {
	}

	/**
	 * Get new instance, it do not has user authenticate credential.
	 * @param configuration
	 * @return Zaim instance
	 * @see OAuthConfiguration
	 * @see OAuthCredential
	 * @author vvakame
	 */
	public static Zaim newInstanceWithUserAuthenticationNotRequired(OAuthConfiguration configuration) {
		Zaim zaim = new Zaim();
		zaim.connector = new OAuthConnector(configuration, null);
		return zaim;
	}

	/**
	 * Get new instance.
	 * @param credential
	 * @return Zaim instance
	 * @author vvakame
	 */
	public static Zaim newInstance(OAuthCredential credential) {
		Zaim zaim = new Zaim();
		zaim.connector = new OAuthConnector(credential.configuration, credential);
		return zaim;
	}

	/**
	 * API about user.
	 * @return user api
	 * @author vvakame
	 */
	public User user() {
		return new User();
	}

	/**
	 * API about money.
	 * @return money api
	 * @author vvakame
	 */
	public Money money() {
		return new Money();
	}

	/**
	 * API about account.
	 * @return account api
	 * @author vvakame
	 */
	public Account account() {
		return new Account();
	}

	/**
	 * API about category.
	 * @return category api
	 * @author vvakame
	 */
	public Category category() {
		return new Category();
	}

	/**
	 * API about genre.
	 * @return genre api
	 * @author vvakame
	 */
	public Genre genre() {
		return new Genre();
	}

	/**
	 * API about other.
	 * @return other api
	 * @author vvakame
	 */
	public Other other() {
		return new Other();
	}


	/**
	 * Zaim API call result listener.
	 * @author vvakame
	 * @param <S>
	 */
	public interface ZaimListener<S> {

		/**
		 * Call on success.
		 * @param success
		 * @author vvakame
		 */
		public void onSuccess(S success);

		/**
		 * Call on failure
		 * @param failure
		 * @author vvakame
		 */
		public void onFailure(ErrorResponse failure);

		/**
		 * Call on error
		 * @param e
		 * @author vvakame
		 */
		public void onError(Exception e);
	}

	interface ResponseConverter<T> {

		public T convert(InputStream is) throws IOException, JsonFormatException;
	}


	<T>void doCallback(HttpURLConnection connection, ZaimListener<T> listener,
			ResponseConverter<T> converter) {

		try {
			final int responseCode = connection.getResponseCode();
			if (responseCode == 200) {
				listener.onSuccess(converter.convert(connection.getInputStream()));
			} else if (responseCode == 401) {
				String message = OAuthConnector.streamToString(connection.getErrorStream());
				listener.onError(new IllegalAccessException(message));
			} else {
				System.err.println("status code:" + connection.getResponseCode());
				listener.onFailure(ErrorResponseGen.get(connection.getErrorStream()));
			}
		} catch (IOException e) {
			listener.onError(e);
		} catch (JsonFormatException e) {
			listener.onError(e);
		}
	}


	/**
	 * User API.
	 * @author vvakame
	 */
	public class User {

		private User() {
		}


		/**
		 * User verify API.
		 * @author vvakame
		 */
		public class Verify {

			private Verify() {
			}

			/**
			 * Get user verify api.
			 * @param listener
			 * @author vvakame
			 */
			public void execute(ZaimListener<UserVerifyResponse> listener) {
				if (listener == null) {
					throw new NullPointerException("listener is required");
				}

				HttpURLConnection connection = connector.doGet("/v2/home/user/verify", null);

				doCallback(connection, listener, new ResponseConverter<UserVerifyResponse>() {

					@Override
					public UserVerifyResponse convert(InputStream is) throws IOException,
							JsonFormatException {
						return UserVerifyResponseGen.get(is);
					}
				});
			}
		}


		/**
		 * user verify api.
		 * @return verify api
		 * @author vvakame
		 */
		public Verify verify() {
			return new Verify();
		}
	}

	/**
	 * Money API.
	 * @author vvakame
	 */
	public class Money {

		private Money() {
		}


		/**
		 * Money list API.
		 * @author vvakame
		 */
		public class List {

			MoneyListArgument arg;


			private List(MoneyListArgument arg) {
				if (arg == null) {
					this.arg = new MoneyListArgument();
				} else {
					this.arg = arg;
				}
			}

			/**
			 * Get money list api.
			 * @param listener
			 * @author vvakame
			 */
			public void execute(ZaimListener<MoneyListResponse> listener) {
				if (listener == null) {
					throw new NullPointerException("listener is required");
				}

				Map<String, String> params = new HashMap<String, String>();
				if (arg.getCategoryId() != null) {
					params.put("category_id", String.valueOf(arg.getCategoryId()));
				}
				if (arg.getGenreId() != null) {
					params.put("genre_id", String.valueOf(arg.getGenreId()));
				}
				if (arg.getMode() != null) {
					params.put("mode", arg.getMode().name().toLowerCase());
				}
				if (arg.getOrder() != null) {
					params.put("order", arg.getOrder().name().toLowerCase());
				}
				if (arg.getStartDate() != null) {
					params.put("start_date", arg.getStartDate());
				}
				if (arg.getEndDate() != null) {
					params.put("end_date", arg.getEndDate());
				}
				if (arg.getPage() != null) {
					params.put("page", String.valueOf(arg.getPage()));
				}
				if (arg.getLimit() != null) {
					params.put("limit", String.valueOf(arg.getLimit()));
				}
				HttpURLConnection connection = connector.doGet("/v2/home/money", params);

				doCallback(connection, listener, new ResponseConverter<MoneyListResponse>() {

					@Override
					public MoneyListResponse convert(InputStream is) throws IOException,
							JsonFormatException {
						return MoneyListResponseGen.get(is);
					}
				});
			}
		}


		/**
		 * Money list api.
		 * @param arg
		 * @return list api
		 * @author vvakame
		 */
		public List list(MoneyListArgument arg) {
			return new List(arg);
		}

		/**
		 * Money list api.
		 * @return list api
		 * @author vvakame
		 */
		public List list() {
			return new List(null);
		}


		/**
		 * Money payment api.
		 * @author vvakame
		 */
		public class Payment {

			static final String API_URL = "/v2/home/money/payment";


			private Payment() {
			}


			/**
			 * Money payment insert api.
			 * @author vvakame
			 */
			public class Insert {

				MoneyPaymentInsertArgument arg;


				private Insert(MoneyPaymentInsertArgument arg) {
					if (arg == null) {
						throw new NullPointerException("argment is reqruired.");
					} else {
						this.arg = arg;
					}
				}

				/**
				 * Money payment insert api.
				 * @param listener
				 * @author vvakame
				 */
				public void execute(ZaimListener<MoneyPostInsertResponse> listener) {
					if (listener == null) {
						throw new NullPointerException("listener is required");
					}

					Map<String, String> params = new HashMap<String, String>();
					// required
					params.put("category_id", String.valueOf(arg.getCategoryId()));
					params.put("genre_id", String.valueOf(arg.getGenreId()));
					params.put("amount", String.valueOf(arg.getAmount()));
					params.put("date", String.valueOf(arg.getDate()));
					// optional
					if (arg.getFromAccountId() != null) {
						params.put("from_account_id", String.valueOf(arg.getFromAccountId()));
					}
					if (arg.getComment() != null) {
						params.put("comment", arg.getComment());
					}
					if (arg.getName() != null) {
						params.put("name", arg.getName());
					}
					if (arg.getPlace() != null) {
						params.put("place", arg.getPlace());
					}

					HttpURLConnection connection = connector.doPost(API_URL, params);

					doCallback(connection, listener,
							new ResponseConverter<MoneyPostInsertResponse>() {

								@Override
								public MoneyPostInsertResponse convert(InputStream is)
										throws IOException, JsonFormatException {
									return MoneyPostInsertResponseGen.get(is);
								}
							});
				}

			}


			/**
			* Money payment insert api.
			* @param arg
			* @return insert api
			* @author vvakame
			*/
			public Insert insert(MoneyPaymentInsertArgument arg) {
				return new Insert(arg);
			}


			/**
			 * Money payment update api.
			 * @author vvakame
			 */
			public class Update {

				MoneyUpdateArgument arg;


				private Update(MoneyUpdateArgument arg) {
					if (arg == null) {
						throw new NullPointerException("argment is reqruired.");
					} else {
						this.arg = arg;
					}
				}

				/**
				 * Get money list api.
				 * @param listener
				 * @author vvakame
				 */
				public void execute(ZaimListener<MoneyModifiedResponse> listener) {
					if (listener == null) {
						throw new NullPointerException("listener is required");
					}

					Map<String, String> params = new HashMap<String, String>();
					// required
					params.put("amount", String.valueOf(arg.getAmount()));
					params.put("date", arg.getDate());
					// optional
					if (arg.getFromAccountId() != null) {
						params.put("from_account_id", String.valueOf(arg.getFromAccountId()));
					}
					if (arg.getToAccountId() != null) {
						params.put("to_account_id", String.valueOf(arg.getToAccountId()));
					}
					if (arg.getGenre() != null) {
						params.put("genre", String.valueOf(arg.getGenre()));
					}
					if (arg.getCategoryId() != null) {
						params.put("category_id", String.valueOf(arg.getCategoryId()));
					}
					if (arg.getComment() != null) {
						params.put("comment", arg.getComment());
					}

					HttpURLConnection connection =
							connector.doPut(API_URL + "/" + arg.getId(), params);

					doCallback(connection, listener,
							new ResponseConverter<MoneyModifiedResponse>() {

								@Override
								public MoneyModifiedResponse convert(InputStream is)
										throws IOException, JsonFormatException {
									return MoneyModifiedResponseGen.get(is);
								}
							});
				}

			}


			/**
			* Money payment update api.
			* @param arg
			* @return insert api
			* @author vvakame
			*/
			public Update update(MoneyUpdateArgument arg) {
				return new Update(arg);
			}


			/**
			 * Money payment delete api.
			 * @author vvakame
			 */
			public class Delete {

				long id;


				private Delete(long id) {
					this.id = id;
				}

				/**
				 * Get money list api.
				 * @param listener
				 * @author vvakame
				 */
				public void execute(ZaimListener<MoneyModifiedResponse> listener) {
					if (listener == null) {
						throw new NullPointerException("listener is required");
					}

					HttpURLConnection connection = connector.doDelete(API_URL + "/" + id, null);

					doCallback(connection, listener,
							new ResponseConverter<MoneyModifiedResponse>() {

								@Override
								public MoneyModifiedResponse convert(InputStream is)
										throws IOException, JsonFormatException {
									return MoneyModifiedResponseGen.get(is);
								}
							});
				}
			}


			/**
			* Money payment delete api.
			* @param id 
			* @return insert api
			* @author vvakame
			*/
			public Delete delete(long id) {
				return new Delete(id);
			}
		}


		/**
		 * Money payment api.
		 * @return payment api
		 * @author vvakame
		 */
		public Payment payment() {
			return new Payment();
		}


		/**
		 * Money income api.
		 * @author vvakame
		 */
		public class Income {

			static final String API_URL = "/v2/home/money/income";


			private Income() {
			}


			/**
			 * Money income insert api.
			 * @author vvakame
			 */
			public class Insert {

				MoneyIncomeInsertArgument arg;


				private Insert(MoneyIncomeInsertArgument arg) {
					if (arg == null) {
						throw new NullPointerException("argment is reqruired.");
					} else {
						this.arg = arg;
					}
				}

				/**
				 * Get money list api.
				 * @param listener
				 * @author vvakame
				 */
				public void execute(ZaimListener<MoneyPostInsertResponse> listener) {
					if (listener == null) {
						throw new NullPointerException("listener is required");
					}

					Map<String, String> params = new HashMap<String, String>();
					// required
					params.put("category_id", String.valueOf(arg.getCategoryId()));
					params.put("amount", String.valueOf(arg.getAmount()));
					params.put("date", String.valueOf(arg.getDate()));
					// optional
					if (arg.getToAccountId() != null) {
						params.put("to_account_id", String.valueOf(arg.getToAccountId()));
					}
					if (arg.getComment() != null) {
						params.put("comment", arg.getComment());
					}

					HttpURLConnection connection = connector.doPost(API_URL, params);

					doCallback(connection, listener,
							new ResponseConverter<MoneyPostInsertResponse>() {

								@Override
								public MoneyPostInsertResponse convert(InputStream is)
										throws IOException, JsonFormatException {
									return MoneyPostInsertResponseGen.get(is);
								}
							});
				}
			}


			/**
			 * Money income insert api.
			 * @param arg
			 * @return income api
			 * @author vvakame
			 */
			public Insert insert(MoneyIncomeInsertArgument arg) {
				return new Insert(arg);
			}


			/**
			 * Money income update api.
			 * @author vvakame
			 */
			public class Update {

				MoneyUpdateArgument arg;


				private Update(MoneyUpdateArgument arg) {
					if (arg == null) {
						throw new NullPointerException("argment is reqruired.");
					} else {
						this.arg = arg;
					}
				}

				/**
				 * Update income api.
				 * @param listener
				 * @author vvakame
				 */
				public void execute(ZaimListener<MoneyModifiedResponse> listener) {
					if (listener == null) {
						throw new NullPointerException("listener is required");
					}

					Map<String, String> params = new HashMap<String, String>();
					// required
					params.put("amount", String.valueOf(arg.getAmount()));
					params.put("date", arg.getDate());
					// optional
					if (arg.getFromAccountId() != null) {
						params.put("from_account_id", String.valueOf(arg.getFromAccountId()));
					}
					if (arg.getToAccountId() != null) {
						params.put("to_account_id", String.valueOf(arg.getToAccountId()));
					}
					if (arg.getGenre() != null) {
						params.put("genre", String.valueOf(arg.getGenre()));
					}
					if (arg.getCategoryId() != null) {
						params.put("category_id", String.valueOf(arg.getCategoryId()));
					}
					if (arg.getComment() != null) {
						params.put("comment", arg.getComment());
					}

					HttpURLConnection connection =
							connector.doPut(API_URL + "/" + arg.getId(), params);

					doCallback(connection, listener,
							new ResponseConverter<MoneyModifiedResponse>() {

								@Override
								public MoneyModifiedResponse convert(InputStream is)
										throws IOException, JsonFormatException {
									return MoneyModifiedResponseGen.get(is);
								}
							});
				}
			}


			/**
			* Money income update api.
			* @param arg
			* @return insert api
			* @author vvakame
			*/
			public Update update(MoneyUpdateArgument arg) {
				return new Update(arg);
			}


			/**
			 * Money income delete api.
			 * @author vvakame
			 */
			public class Delete {

				long id;


				private Delete(long id) {
					this.id = id;
				}

				/**
				 * Delete income api.
				 * @param listener
				 * @author vvakame
				 */
				public void execute(ZaimListener<MoneyModifiedResponse> listener) {
					if (listener == null) {
						throw new NullPointerException("listener is required");
					}

					HttpURLConnection connection = connector.doDelete(API_URL + "/" + id, null);

					doCallback(connection, listener,
							new ResponseConverter<MoneyModifiedResponse>() {

								@Override
								public MoneyModifiedResponse convert(InputStream is)
										throws IOException, JsonFormatException {
									return MoneyModifiedResponseGen.get(is);
								}
							});
				}
			}


			/**
			* Money payment delete api.
			* @param id 
			* @return insert api
			* @author vvakame
			*/
			public Delete delete(long id) {
				return new Delete(id);
			}
		}


		/**
		 * Money income api.
		 * @return income api
		 * @author vvakame
		 */
		public Income income() {
			return new Income();
		}


		/**
		 * Money transfer api.
		 * @author vvakame
		 */
		public class Transfer {

			static final String API_URL = "/v2/home/money/transfer";


			private Transfer() {
			}


			/**
			 * Money transfer insert api.
			 * @author vvakame
			 */
			public class Insert {

				MoneyTransferInsertArgument arg;


				private Insert(MoneyTransferInsertArgument arg) {
					if (arg == null) {
						throw new NullPointerException("argment is reqruired.");
					} else {
						this.arg = arg;
					}
				}

				/**
				 * Money transfer insert api.
				 * @param listener
				 * @author vvakame
				 */
				public void execute(ZaimListener<MoneyPostInsertResponse> listener) {
					if (listener == null) {
						throw new NullPointerException("listener is required");
					}

					Map<String, String> params = new HashMap<String, String>();
					// required
					params.put("amount", String.valueOf(arg.getAmount()));
					params.put("date", String.valueOf(arg.getDate()));
					params.put("from_account_id", String.valueOf(arg.getFromAccountId()));
					params.put("to_account_id", String.valueOf(arg.getToAccountId()));
					// optional
					if (arg.getComment() != null) {
						params.put("comment", arg.getComment());
					}

					HttpURLConnection connection = connector.doPost(API_URL, params);

					doCallback(connection, listener,
							new ResponseConverter<MoneyPostInsertResponse>() {

								@Override
								public MoneyPostInsertResponse convert(InputStream is)
										throws IOException, JsonFormatException {
									return MoneyPostInsertResponseGen.get(is);
								}
							});
				}
			}


			/**
			 * Money transfer insert api.
			 * @param arg
			 * @return transfer api
			 * @author vvakame
			 */
			public Insert insert(MoneyTransferInsertArgument arg) {
				return new Insert(arg);
			}


			/**
			 * Money transfer update api.
			 * @author vvakame
			 */
			public class Update {

				MoneyUpdateArgument arg;


				private Update(MoneyUpdateArgument arg) {
					if (arg == null) {
						throw new NullPointerException("argment is reqruired.");
					} else {
						this.arg = arg;
					}
				}

				/**
				 * Update transfer api.
				 * @param listener
				 * @author vvakame
				 */
				public void execute(ZaimListener<MoneyModifiedResponse> listener) {
					if (listener == null) {
						throw new NullPointerException("listener is required");
					}

					Map<String, String> params = new HashMap<String, String>();
					// required
					params.put("amount", String.valueOf(arg.getAmount()));
					params.put("date", arg.getDate());
					// optional
					if (arg.getFromAccountId() != null) {
						params.put("from_account_id", String.valueOf(arg.getFromAccountId()));
					}
					if (arg.getToAccountId() != null) {
						params.put("to_account_id", String.valueOf(arg.getToAccountId()));
					}
					if (arg.getGenre() != null) {
						params.put("genre", String.valueOf(arg.getGenre()));
					}
					if (arg.getCategoryId() != null) {
						params.put("category_id", String.valueOf(arg.getCategoryId()));
					}
					if (arg.getComment() != null) {
						params.put("comment", arg.getComment());
					}

					HttpURLConnection connection =
							connector.doPut(API_URL + "/" + arg.getId(), params);

					doCallback(connection, listener,
							new ResponseConverter<MoneyModifiedResponse>() {

								@Override
								public MoneyModifiedResponse convert(InputStream is)
										throws IOException, JsonFormatException {
									return MoneyModifiedResponseGen.get(is);
								}
							});
				}
			}


			/**
			* Money transfer update api.
			* @param arg
			* @return transfer api
			* @author vvakame
			*/
			public Update update(MoneyUpdateArgument arg) {
				return new Update(arg);
			}


			/**
			 * Money transfer delete api.
			 * @author vvakame
			 */
			public class Delete {

				long id;


				private Delete(long id) {
					this.id = id;
				}

				/**
				 * Delete transfer api.
				 * @param listener
				 * @author vvakame
				 */
				public void execute(ZaimListener<MoneyModifiedResponse> listener) {
					if (listener == null) {
						throw new NullPointerException("listener is required");
					}

					HttpURLConnection connection = connector.doDelete(API_URL + "/" + id, null);

					doCallback(connection, listener,
							new ResponseConverter<MoneyModifiedResponse>() {

								@Override
								public MoneyModifiedResponse convert(InputStream is)
										throws IOException, JsonFormatException {
									return MoneyModifiedResponseGen.get(is);
								}
							});
				}
			}


			/**
			* Money transfer delete api.
			* @param id 
			* @return transfer api
			* @author vvakame
			*/
			public Delete delete(long id) {
				return new Delete(id);
			}
		}


		/**
		 * Money transfer api.
		 * @return transfer api
		 * @author vvakame
		 */
		public Transfer transfer() {
			return new Transfer();
		}
	}

	/**
	 * Category API.
	 * @author vvakame
	 */
	public class Category {

		private Category() {
		}


		/**
		 * Category list API.
		 * @author vvakame
		 */
		public class List {

			MoneyMode arg;


			// TODO transfer は取らない…
			private List(MoneyMode arg) {
				this.arg = arg;
			}

			/**
			 * Get category list api.
			 * @param listener
			 * @author vvakame
			 */
			public void execute(ZaimListener<CategoryListResponse> listener) {
				if (listener == null) {
					throw new NullPointerException("listener is required");
				}

				Map<String, String> params = new HashMap<String, String>();
				if (arg != null) {
					params.put("mode", arg.name().toLowerCase());
				}
				HttpURLConnection connection = connector.doGet("/v2/home/category", params);

				doCallback(connection, listener, new ResponseConverter<CategoryListResponse>() {

					@Override
					public CategoryListResponse convert(InputStream is) throws IOException,
							JsonFormatException {
						return CategoryListResponseGen.get(is);
					}
				});
			}
		}


		/**
		 * Category list api.
		 * @param arg
		 * @return list api
		 * @author vvakame
		 */
		public List list(MoneyMode arg) {
			return new List(arg);
		}

		/**
		 * Category list api.
		 * @return list api
		 * @author vvakame
		 */
		public List list() {
			return new List(null);
		}
	}

	/**
	 * Genre API.
	 * @author vvakame
	 */
	public class Genre {

		private Genre() {
		}


		/**
		 * Genre list API.
		 * @author vvakame
		 */
		public class List {

			MoneyMode arg;


			// TODO transfer は取らない…
			private List(MoneyMode arg) {
				this.arg = arg;
			}

			/**
			 * Get category list api.
			 * @param listener
			 * @author vvakame
			 */
			public void execute(ZaimListener<GenreListResponse> listener) {
				if (listener == null) {
					throw new NullPointerException("listener is required");
				}

				Map<String, String> params = new HashMap<String, String>();
				if (arg != null) {
					params.put("mode", arg.name().toLowerCase());
				}
				HttpURLConnection connection = connector.doGet("/v2/home/genre", params);

				doCallback(connection, listener, new ResponseConverter<GenreListResponse>() {

					@Override
					public GenreListResponse convert(InputStream is) throws IOException,
							JsonFormatException {
						return GenreListResponseGen.get(is);
					}
				});
			}
		}


		/**
		 * Category list api.
		 * @param arg
		 * @return list api
		 * @author vvakame
		 */
		public List list(MoneyMode arg) {
			return new List(arg);
		}

		/**
		 * Category list api.
		 * @return list api
		 * @author vvakame
		 */
		public List list() {
			return new List(null);
		}
	}

	/**
	 * Account API.
	 * @author vvakame
	 */
	public class Account {

		private Account() {
		}


		/**
		 * Account list API.
		 * @author vvakame
		 */
		public class List {

			MoneyMode arg;


			// TODO transfer は取らない…
			private List(MoneyMode arg) {
				this.arg = arg;
			}

			/**
			 * Get account list api.
			 * @param listener
			 * @author vvakame
			 */
			public void execute(ZaimListener<AccountListResponse> listener) {
				if (listener == null) {
					throw new NullPointerException("listener is required");
				}

				Map<String, String> params = new HashMap<String, String>();
				if (arg != null) {
					params.put("mode", arg.name().toLowerCase());
				}
				HttpURLConnection connection = connector.doGet("/v2/home/account", params);

				doCallback(connection, listener, new ResponseConverter<AccountListResponse>() {

					@Override
					public AccountListResponse convert(InputStream is) throws IOException,
							JsonFormatException {
						return AccountListResponseGen.get(is);
					}
				});
			}
		}


		/**
		 * Category list api.
		 * @param arg
		 * @return list api
		 * @author vvakame
		 */
		public List list(MoneyMode arg) {
			return new List(arg);
		}

		/**
		 * Category list api.
		 * @return list api
		 * @author vvakame
		 */
		public List list() {
			return new List(null);
		}
	}

	/**
	 * Other API.
	 * @author vvakame
	 */
	public class Other {

	}
}
